package com.data.hope.utils;


import com.data.hope.annotation.DtoSkip;
import com.data.hope.annotation.Formatter;
import com.data.hope.annotation.FormatterType;
import com.data.hope.cache.CacheHelper;
import com.data.hope.ReportUtils;
import com.data.hope.constant.Enabled;
import com.data.hope.constant.ReportConstant;
import com.data.hope.constant.ReportKeyConstant;
import com.data.hope.holder.UserContentHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 字段翻译
 *
 * @author guoqinghua
 * @since 2022-03-01
 */
public abstract class ReportBeanUtils {

    private static final Logger logger = LoggerFactory.getLogger(ReportBeanUtils.class);

    /**
     * 字段类型转换
     *
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T copyAndFormatter(Object source, T target) {
        //获取目标类并翻译
        Field[] declaredFields = target.getClass().getDeclaredFields();
        List<Field> fields = new ArrayList<>(declaredFields.length);

        //跳过翻译的字段
        List<String> skipFields = new ArrayList<>();

        //非普通类型的翻译即对象，需要翻译其对象
        List<Field> formatterTypeFields = new ArrayList<>();

        //过滤掉DtoSkip注解的字段
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(DtoSkip.class)) {
                skipFields.add(field.getName());
                continue;
            }

            if (field.isAnnotationPresent(FormatterType.class)) {
                formatterTypeFields.add(field);
                continue;
            }

            fields.add(field);
        }
        //都要放过
        skipFields.addAll(formatterTypeFields.stream().map(Field::getName).collect(Collectors.toList()));

        //entity翻译成DTO,跳过忽略DtoSkip的字段和类型翻译FormatterType的字段
        BeanUtils.copyProperties(source, target, skipFields.toArray(new String[0]));

        Field[] superDeclaredFields = target.getClass().getSuperclass().getDeclaredFields();
        fields.addAll(Arrays.asList(superDeclaredFields));

        //遍历字段，找出 Formatter注解注释的字段,并翻译
        formatterHandler(source,target, fields);

        //翻译非基础类型即：对象或者集合

        for (Field field : formatterTypeFields) {
            try {
                PropertyDescriptor sourcePropertyDescriptor = new PropertyDescriptor(field.getName(), source.getClass());
                PropertyDescriptor targetPropertyDescriptor = new PropertyDescriptor(field.getName(), target.getClass());
                Object fieldSource = sourcePropertyDescriptor.getReadMethod().invoke(source);
                if (fieldSource == null) {
                    continue;
                }
                Method writeMethod = targetPropertyDescriptor.getWriteMethod();
                switch (field.getAnnotation(FormatterType.class).type()) {
                    case OBJECT:
                        Object fieldTarget = field.getType().newInstance();
                        copyAndFormatter(fieldSource, fieldTarget);
                        writeMethod.invoke(target, fieldTarget);
                        break;
                    case LIST:
                        Type genericType = field.getGenericType();
                        ParameterizedType parameterizedType = (ParameterizedType) genericType;
                        Class fieldTargetClass = (Class) parameterizedType.getActualTypeArguments()[0];
                        List fieldTargetList = copyList((List) fieldSource, fieldTargetClass);
                        writeMethod.invoke(target, fieldTargetList);
                        break;
                    default:
                }

            } catch (Exception e) {
                logger.error("FormatterType处理异常", e);
                continue;
            }
        }

        return target;
    }

    /**
     * 复制集合
     * @param sourceList
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List<? extends Object> sourceList, Class<T> targetClass) {
        return sourceList.stream().map(source -> {
            try {
                T target = targetClass.newInstance();
                copyAndFormatter(source, target);
                return target;
            } catch (Exception e) {
                logger.error("集合翻译失败,目标类：" + targetClass.getName(), e);
            }
            return null;
        }).collect(Collectors.toList());
    }

    /**
     * 翻译被Formatter注解的字段
     * @param target
     * @param fields
     * @param <T>
     */
    private static <T> void formatterHandler(Object source, T target, List<Field> fields) {
        //遍历字段，找出 Formatter注解注释的字段
        //获取对应字典中的值
        //国际化
        Locale locale = LocaleContextHolder.getLocale();
        //语言
        String language = locale.getLanguage();
        Map<String, Object> params = UserContentHolder.getContext().getParams();

        fields.stream().parallel().filter(field -> field.isAnnotationPresent(com.data.hope.annotation.Formatter.class)).forEach(field -> {
            try {
                //判断是否有注解Formatter
                PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), target.getClass());
                Method readMethod = descriptor.getReadMethod();
                //读取属性值
                Object result = readMethod.invoke(target);
                if (result instanceof Boolean) {
                    result = (Boolean) result ? Enabled.YES.getValue() : Enabled.NO.getValue();
                }

                //非空判断
                if (result != null) {
                    com.data.hope.annotation.Formatter annotation = field.getAnnotation(Formatter.class);

                    CacheHelper cacheHelper = ApplicationContextUtils.getBean(CacheHelper.class);
                    String key;
                    if (StringUtils.isBlank(annotation.key())) {
                        String dictCode = annotation.dictCode();

                        key = ReportKeyConstant.DICT_PREFIX + language+ ReportConstant.REDIS_SPLIT +dictCode;
                    } else {
                        //支持动态key，比如key=DICT:${DEMO}，注解Formatter的replace=["demo"]
                        key = formatKey(annotation.key(), annotation.replace(),params, source);
                    }

                    //TODO 只需第一次从Redis获取并缓存
                    String hashKey = result.toString();
                    String dictValue = cacheHelper.hashGetString(key, hashKey);

                    if (StringUtils.isBlank(dictValue)) {
                        dictValue = cacheHelper.hashGetString(key, hashKey.toLowerCase());
                    }

                    if (StringUtils.isNotBlank(dictValue)) {
                        PropertyDescriptor descriptorTarget = new PropertyDescriptor(field.getName(), target.getClass());
                        if (StringUtils.isBlank(annotation.targetField())) {
                            Method writeMethod = descriptorTarget.getWriteMethod();
                            writeMethod.invoke(target, dictValue);
                        } else {
                            descriptorTarget = new PropertyDescriptor(annotation.targetField(), target.getClass());
                            if (descriptorTarget != null) {
                                descriptorTarget.getWriteMethod().invoke(target, dictValue);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 替换占位符key
     * @param key
     * @param replaceArray 替换
     * @param source
     * @return
     */
    public static String formatKey(String key, String[] replaceArray, Map<String, Object> params, Object source) {
        if (key.contains(ReportConstant.URL_PATTERN_MARK)) {
            Map<String,Object> keyPatternMap = new HashMap<>(2);
            for (String fieldName : replaceArray) {
                try {
                    Object value = params.get(fieldName);
                    if (null == value) {
                        Field declaredField = source.getClass().getDeclaredField(fieldName);
                        declaredField.setAccessible(true);
                        value = declaredField.get(source);
                    }
                    keyPatternMap.put(fieldName,value);
                } catch (Exception e) {
                    continue;
                }
            }

            key = ReportUtils.replaceFormatString(key, keyPatternMap);
            if (key.contains(ReportConstant.URL_PATTERN_MARK)) {
                return null;
            }
        }

        return key;
    }
}
