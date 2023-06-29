package com.data.hope.annotation.valid.keyvalue;

import com.data.hope.cache.CacheHelper;
import com.data.hope.constant.ReportConstant;
import com.data.hope.constant.ReportKeyConstant;
import com.data.hope.holder.UserContentHolder;
import com.data.hope.utils.ApplicationContextUtils;
import com.data.hope.ReportUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * 校验注解
 * @author guoqinghua
 * @since 2022-03-01
 */
public class AssertKeyValueValidator implements ConstraintValidator<AssertKeyValue, Object> {

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 缓存key
     */
    private String key;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        CacheHelper cacheHelper = ApplicationContextUtils.getBean(CacheHelper.class);

        //当不存在缓存时，直接跳过
        if (cacheHelper == null) {
            return true;
        }

        //当值为null时
        if (value == null) {
            return true;
        }

        //国际化
        String locale = LocaleContextHolder.getLocale().getLanguage();

        Map<String, String> map = new HashMap<>();

        //判断字典
        if (StringUtils.isNotBlank(dictCode)) {
            String dictKey = ReportKeyConstant.DICT_PREFIX + locale + ReportConstant.REDIS_SPLIT + dictCode;
            map = cacheHelper.hashGet(dictKey);
        } else if (StringUtils.isNotBlank(key)) {
            if (key.contains(ReportConstant.URL_PATTERN_MARK)) {
                Map<String, Object> params = UserContentHolder.getContext().getParams();
                key = ReportUtils.replaceFormatString(key, params);
            }
            map = cacheHelper.hashGet(key);
        } else {
            //当dictCode与key同时为空时，放过不校验
            return true;
        }

        //当存在逗号隔开时
        if(String.valueOf(value).contains(ReportConstant.SPLIT)) {
            String[] values = ((String) value).split(ReportConstant.SPLIT);
            for (String v : values) {
                if (!map.containsKey(v)) {
                    return false;
                }
            }
            return true;
        } else if (map.containsKey(String.valueOf(value))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initialize(AssertKeyValue assertKeyValue) {
        this.dictCode = assertKeyValue.dictCode();
        this.key = assertKeyValue.key();
    }
}
