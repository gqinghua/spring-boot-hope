package com.data.hope.curd.service;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.data.hope.annotation.*;
import com.data.hope.cache.CacheHelper;
import com.data.hope.constant.BaseOperationEnum;
import com.data.hope.constant.QueryEnum;
import com.data.hope.constant.ReportConstant;
import com.data.hope.curd.entity.BaseEntity;
import com.data.hope.curd.entity.ReportBaseEntity;
import com.data.hope.curd.mapper.ReportBaseMapper;
import com.data.hope.exception.BusinessException;
import com.data.hope.exception.BusinessExceptionBuilder;
import com.data.hope.utils.ApplicationContextUtils;
import com.data.hope.utils.ReportAssert;
import com.sg.hope.annotation.*;
import com.data.hope.ReportUtils;
import com.sg.nr.annotation.*;
import com.data.hope.bean.HashKeyValue;
import com.data.hope.curd.params.PageParam;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static com.data.hope.code.ResponseCode.*;


/**
 * 基础service
 *
 * @author guoqinghua
 * @since 2022-03-01
 */
public interface ReportBaseService<P extends PageParam, T extends BaseEntity>  {

    /**
     * 获取直接操作数据库接口
     *
     * @return
     */
    ReportBaseMapper<T> getMapper();

    /**
     * 包装返回实体
     *
     * @param entity
     * @return
     */
    default T wrapperEntity(T entity) {
        return entity;
    }

    default List<T> wrapperListEntity(List<T> entity) {
        return entity;
    }
    /**
     * 根据id查询记录
     *
     * @param id
     * @return
     */
    default T selectOne(Long id) {
        T t = getMapper().selectById(id);
        ReportAssert.notNull(t, RECORD_NO_EXIST);
        return wrapperEntity(t);
    }

    default T selectOne(String id) {
        T t = getMapper().selectById(id);
        ReportAssert.notNull(t, SUCCESS_CODE);
        return wrapperEntity(t);
    }

    /**
     * 根据条件查询唯一字段
     * @param wrapper
     * @return
     */
    default T selectOne(Wrapper<T> wrapper) {
        return getMapper().selectOne(wrapper);
    }

    /**
     * 根据指定字段查询唯一记录
     *
     * @param column 字段
     * @param value  字段对应的值
     * @return
     */
    default T selectOne(String column, Object value) {
        List<T> list = list(column, value);

        if (list.isEmpty()) {
            return null;
        }
        return wrapperEntity(list.get(0));
    }


    /**
     * 根据指定字段查询唯一记录
     *
     * @param column 字段
     * @param value  字段对应的值
     * @return
     */
    default List<T> selectList(String column, Object value) {
        List<T> list = list(column, value);

        if (list.isEmpty()) {
            return null;
        }
        return wrapperListEntity(list);
    }
    /**
     * 分页
     *
     * @param pageParam
     * @return
     */
    default IPage<T> page(P pageParam) {
        return page(pageParam, null);
    }

    /**
     * 构建排序
     * @param column
     * @param order
     * @return
     */
    default OrderItem build(String column, String order) {
        OrderItem item = new OrderItem();
        item.setColumn(column);

        if (ReportConstant.ASC.equalsIgnoreCase(order)) {
            item.setAsc(true);
        } else {
            item.setAsc(false);
        }

        return item;
    }

    /**
     * 分页，指定查询条件即忽略扩展的条件
     *
     * @param pageParam
     * @param wrapper   指定参数
     * @return
     */
    default IPage<T> page(P pageParam, Wrapper<T> wrapper) {
        Page<T> page = new Page<>();
        page.setCurrent(pageParam.getPageNum());
        page.setSize(pageParam.getPageSize());

        //设置排序字段
        String sort = pageParam.getSort();

        //升序还是降序
        String order = pageParam.getOrder();
        String[] sortSplit = {};
        String[] orderSplit = {};

        //判断,
        if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {

            //多字段有升序有降序，长度必须一样
            if (sort.contains(ReportConstant.SPLIT) && order.contains(ReportConstant.SPLIT)) {
                sortSplit = sort.split(ReportConstant.SPLIT);
                orderSplit = order.split(ReportConstant.SPLIT);
                if (orderSplit.length == sortSplit.length) {
                    OrderItem[] orderItems = new OrderItem[orderSplit.length];
                    for (int i=0;i< sortSplit.length;i++) {
                        orderItems[i] = build(ReportUtils.camelToUnderline(sortSplit[i]), orderSplit[i]);
                    }
                    page.addOrder(orderItems);
                } else {
                    String order0 = orderSplit[0];
                    OrderItem[] orderItems = new OrderItem[orderSplit.length];
                    for (int i=0;i< sortSplit.length;i++) {
                        orderItems[i] = build(ReportUtils.camelToUnderline(sortSplit[i]), order0);
                    }
                    page.addOrder(orderItems);
                }
            }

            //当只有列有间隔符时
            if (sort.contains(ReportConstant.SPLIT) && !order.contains(ReportConstant.SPLIT)) {
                List<String> orderList = Arrays.stream(sort.split(ReportConstant.SPLIT)).map(ReportUtils::camelToUnderline).collect(Collectors.toList());
                String[] orderColumns = orderList.toArray(new String[]{});
                if (ReportConstant.ASC.equalsIgnoreCase(order)) {
                    page.addOrder(OrderItem.ascs(orderColumns));
                } else {
                    page.addOrder(OrderItem.descs(orderColumns));
                }

            }

            //都没有分割符时
            if (!sort.contains(ReportConstant.SPLIT) && !order.contains(ReportConstant.SPLIT)) {
                page.addOrder(build(ReportUtils.camelToUnderline(sort), order));
            }
        }

        //当有自定义条件时，去掉参数组成的查询条件
        if (wrapper != null) {
            return resultHandler(getMapper().selectPage(page, wrapper));
        }

        Wrapper<T> pageWrapper = extensionWrapper(pageParam, getWrapper(pageParam));

        //扩展点：对条件进行处理
        handlerPageWrapper(pageWrapper);

        return resultHandler(getMapper().selectPage(page, pageWrapper));
    }


    /**
     * 对查询条件进行最后的处理
     * @param pageWrapper
     * @return
     */
    default Wrapper<T> handlerPageWrapper(Wrapper<T> pageWrapper) {
        return pageWrapper;
    }

    /**
     * 排序结果处理(作用：需要对排序结果进行处理时，使用)
     *
     * @param iPage
     * @return
     */
    default IPage<T> resultHandler(IPage<T> iPage) {
        return iPage;
    }

    /**
     * 扩展查询条件
     *
     * @param param   查询参数
     * @param wrapper 基本查询条件
     * @return
     */
    default Wrapper<T> extensionWrapper(P param, QueryWrapper<T> wrapper) {
        return wrapper;
    }

    /**
     * 抽象查询条件
     *
     * @param param 查询条件
     * @return
     */
    default QueryWrapper<T> getWrapper(P param) {

        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        //条件的值
        Field[] fields = param.getClass().getDeclaredFields();

        Arrays.stream(fields).filter(field -> {
            if (field.isAnnotationPresent(Query.class)) {
                Query query = field.getAnnotation(Query.class);
                return query.where();
            }
            return true;
        }).forEach(field -> {
            try {
                boolean flag;
                field.setAccessible(true);
                //列名
                String column;
                if (field.isAnnotationPresent(Query.class) && StringUtils.isNotBlank(field.getAnnotation(Query.class).column())) {
                    column = field.getAnnotation(Query.class).column();
                } else {
                    column = ReportUtils.camelToUnderline(field.getName());
                }

                if (field.get(param) instanceof String) {
                    flag = StringUtils.isNoneBlank((String) field.get(param));
                } else {
                    flag = field.get(param) != null;
                }
                if (!flag) {
                    return;
                }

                //判断是否是模糊查询
                if (field.isAnnotationPresent(Query.class)) {
                    switch (field.getAnnotation(Query.class).value()) {
                        case QueryEnum.LIKE:
                            String likeValue = String.valueOf(field.get(param));

                            if (likeValue.contains("%")) {
                                likeValue = likeValue.replace("%","\\%");
                            }

                            if (likeValue.contains("_")) {
                                likeValue = likeValue.replace("_","\\_");
                            }
                            queryWrapper.like(column, likeValue);
                            break;
                        case QueryEnum.IN:
                            Object value = field.get(param);
                            if (value instanceof List) {
                                queryWrapper.in(column, (List)value);
                            } else if (value instanceof String) {
                                String[] split = ((String) value).split(ReportConstant.SPLIT);
                                List<String> list = Arrays.asList(split);
                                queryWrapper.in(column, list);
                            }
                            break;
                        case QueryEnum.GT:
                            queryWrapper.gt(column, field.get(param));
                            break;
                        case QueryEnum.GE:
                            queryWrapper.ge(column, field.get(param));
                            break;
                        case QueryEnum.LT:
                            queryWrapper.lt(column, field.get(param));
                            break;
                        case QueryEnum.LE:
                            queryWrapper.le(column, field.get(param));
                            break;
                        case QueryEnum.BWT:
                            String[] split = field.get(param).toString().split(ReportConstant.SPLIT);
                            if (split.length == 2) {
                                queryWrapper.between(column, split[0], split[1]);
                            } else if(split.length == 1) {
                                queryWrapper.ge(column, split[0]);
                            }
                            break;
                        default:
                            queryWrapper.eq(column, field.get(param));
                    }

                } else {
                    queryWrapper.eq(column, field.get(param));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return queryWrapper;
    }

    /**
     * 操作前处理
     *
     * @param entity        前端传递的对象
     * @param operationEnum 操作类型
     * @throws BusinessException 阻止程序继续执行或回滚事务
     */
    default void processBeforeOperation(T entity, BaseOperationEnum operationEnum) throws BusinessException {
    }

    /**
     * 操作后续处理
     *
     * @param entity
     * @param operationEnum 操作类型
     * @throws BusinessException 阻止程序继续执行或回滚事务
     */
    default void processAfterOperation(T entity, BaseOperationEnum operationEnum) throws BusinessException {
    }

    /**
     * lambda表达式转换成列名
     *
     * @param function
     * @return
     */
//    default String getColumn(SFunction<T, ?> function) {
//        SerializedLambda lambda = LambdaUtils.resolve(function);
//        String fieldName = PropertyNamer.methodToProperty(lambda.getImplMethodName());
//        Class<?> implClass = lambda.getImplClass();
//
//        try {
//            Field field = implClass.getDeclaredField(fieldName);
//            if (field.isAnnotationPresent(TableField.class)) {
//                fieldName = field.getAnnotation(TableField.class).value();
//                return fieldName;
//            }
//        } catch (NoSuchFieldException e) {
//
//        }
//        return ReportUtils.camelToUnderline(fieldName);
//    }


    /**
     * 保存数据
     *
     * @param entity
     * @return
     * @throws BusinessException 业务异常
     */
    @Transactional(rollbackFor = Exception.class)
    default Integer insert(T entity) throws BusinessException {
        //保存前处理
        processBeforeOperation(entity, BaseOperationEnum.INSERT);

        //校验唯一索引
        checkUniqueField(entity, false);
        //保存
        Integer result = getMapper().insert(entity);

        //保存失败
        if (result == null || result < 1) {
            throw BusinessExceptionBuilder.build(INSERT_FAILURE);
        }

        //保存缓存字段
        refreshCacheFields(entity, BaseOperationEnum.INSERT);
        //保存后处理
        processAfterOperation(entity, BaseOperationEnum.INSERT);

        return result;
    }

    /**
     * 刷新对应字段的缓存
     * @param entity
     * @param operationEnum 操作类型
     */
    default void refreshCacheFields(T entity, BaseOperationEnum operationEnum) {

        //更新缓存
        Class<? extends BaseEntity> entityClass = entity.getClass();
        Field[] declaredFields = entityClass.getDeclaredFields();

        Map<String,HashKeyValue> cacheMap = new HashMap<>();

        for(Field field : declaredFields) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(entity);
            } catch (IllegalAccessException e) {
                continue;
            }
            if (value == null) {
                continue;
            }

            if (field.isAnnotationPresent(HashKey.class)) {

                HashKey hashKey = field.getAnnotation(HashKey.class);
                String key = hashKey.key();

                //判断key是否存在
                if (cacheMap.containsKey(key)) {
                    HashKeyValue hashKeyValue = cacheMap.get(key);
                    hashKeyValue.setKey(String.valueOf(value));
                    hashKeyValue.setHashKey(hashKey);
                } else {
                    HashKeyValue hashKeyValue = new HashKeyValue();
                    hashKeyValue.setKey(String.valueOf(value));
                    hashKeyValue.setHashKey(hashKey);
                    cacheMap.put(key, hashKeyValue);
                }
            }

            if (field.isAnnotationPresent(HashValue.class)) {
                HashValue hashValue = field.getAnnotation(HashValue.class);
                String key = hashValue.key();
                //判断key是否存在
                if (cacheMap.containsKey(key)) {
                    HashKeyValue hashKeyValue = cacheMap.get(key);
                    hashKeyValue.setValue(String.valueOf(value));
                } else {
                    HashKeyValue hashKeyValue = new HashKeyValue();
                    hashKeyValue.setValue(String.valueOf(value));
                    cacheMap.put(key, hashKeyValue);
                }
            }
        }

        //缓存操作类
        CacheHelper cacheHelper = ApplicationContextUtils.getBean(CacheHelper.class);
//        ApplicationContextUtils.
        if (BaseOperationEnum.DELETE == operationEnum || BaseOperationEnum.DELETE_BATCH == operationEnum) {
            //删除缓存
            cacheMap.entrySet().stream()
                    .filter(entry -> entry.getValue().getKey() != null && entry.getValue().getValue() != null)
                    .forEach(entry -> cacheHelper.hashDel(formatKey(entry.getKey(),entry.getValue().getHashKey().replace(), entity), entry.getValue().getKey()));

        }else {
            //刷新缓存，过滤掉HashKeyValue中，key为null或者value为null的情况
            cacheMap.entrySet().stream()
                    .filter(entry -> entry.getValue().getKey() != null && entry.getValue().getValue() != null)
                    .forEach(entry -> cacheHelper.hashSet(formatKey(entry.getKey(),entry.getValue().getHashKey().replace(), entity), entry.getValue().getKey(), entry.getValue().getValue()));
        }
    }

    /**
     * 替换占位符key
     * @param key
     * @param replaceArray 替换
     * @param entity
     * @return
     */
    default String formatKey(String key, String[] replaceArray, T entity) {
        if (key.contains(ReportConstant.URL_PATTERN_MARK)) {
            Map<String,Object> keyPatternMap = new HashMap<>(2);
            for (String fieldName : replaceArray) {
                try {
                    Field declaredField = entity.getClass().getDeclaredField(fieldName);
                    declaredField.setAccessible(true);
                    Object fieldValue = declaredField.get(entity);
                    keyPatternMap.put(fieldName,fieldValue);
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


    /**
     * 保存数据
     *
     * @param entities
     * @return
     * @throws BusinessException 业务异常
     */
    @Transactional(rollbackFor = Exception.class)
    default Integer insertBatch(List<T> entities) throws BusinessException {

        //保存
        Integer result = getMapper().insertBatch(entities);

        //保存缓存
        entities.stream().forEach(entity -> {
            //保存缓存字段
            refreshCacheFields(entity, BaseOperationEnum.INSERT);
        });

        //保存失败
        if (result == null || result < 1) {
            throw BusinessExceptionBuilder.build(INSERT_FAILURE);
        }
        return result;
    }

    /**
     * 更新数据
     *
     * @param entity
     * @return
     * @throws BusinessException 业务异常
     */
    @Transactional(rollbackFor = Exception.class)
    default Integer update(T entity) throws BusinessException {
        //更新前处理
        processBeforeOperation(entity, BaseOperationEnum.UPDATE);
        //校验唯一索引
        checkUniqueField(entity, true);

        if (entity instanceof ReportBaseEntity) {
            ReportBaseEntity BaseEntity = (ReportBaseEntity) entity;
            T dbEntity = getById(BaseEntity.getId());
            refreshCacheFields(dbEntity, BaseOperationEnum.DELETE);
        }

        //更新
        Integer result = getMapper().updateById(entity);

        //更新失败
        if (result == null || result < 1) {
            throw BusinessExceptionBuilder.build(UPDATE_FAILURE);
        }

        //保存缓存字段
        refreshCacheFields(entity, BaseOperationEnum.UPDATE);
        //更新后处理
        processAfterOperation(entity, BaseOperationEnum.UPDATE);
        return result;
    }

    /**
     * 根据id更新指定值
     * @param map
     * @param id
     * @return
     */
    default Integer updateFieldsById(Map<String, Object> map, Long id) {
        return getMapper().updateFieldsById(map, id);
    }


    /**
     * 根据id批量更新指定值
     * @param map
     * @param ids
     * @return
     */
    default Integer updateBatchFieldsById(Map<String, Object> map, List<Long> ids) {
        return getMapper().updateFieldsBatchById(map, ids);
    }

    /**
     * 批量更新
     * @param map
     * @param list
     * @return
     */
    default Integer updateBatchFields(Map<String, Object> map, List<T> list) {
        return getMapper().updateFieldsBatch(map, list);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    default Integer deleteById(Serializable id) {
        T t = getById(id);
        if (t == null) {
            throw BusinessExceptionBuilder.build(RECORD_NO_EXIST);
        }
        //删除前处理
        processBeforeOperation(t, BaseOperationEnum.DELETE);
        Integer result = getMapper().deleteById(id);

        //删除失败
        if (result == null || result < 1) {
            throw BusinessExceptionBuilder.build(DELETE_FAILURE);
        }

        //保存缓存字段
        refreshCacheFields(t, BaseOperationEnum.DELETE);

        //删除后处理
        processAfterOperation(t, BaseOperationEnum.DELETE);
        return result;
    }

    /**
     * 批处理操作前处理
     *
     * @param entities        前端传递的对象
     * @param operationEnum 操作类型
     * @throws BusinessException 阻止程序继续执行或回滚事务
     */
    default void processBatchBeforeOperation(List<T> entities, BaseOperationEnum operationEnum) throws BusinessException {
    }

    /**
     * 批处理操作后续处理
     *
     * @param entities
     * @param operationEnum 操作类型
     * @throws BusinessException 阻止程序继续执行或回滚事务
     */
    default void processBatchAfterOperation(List<T> entities, BaseOperationEnum operationEnum) throws BusinessException {
    }

    /**
     * 批量删除
     * @param idList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    default boolean deleteByIds(Collection<? extends Serializable> idList) {
        List<T> list = getMapper().selectBatchIds(idList);
        processBatchBeforeOperation(list,BaseOperationEnum.DELETE_BATCH);
        boolean result = SqlHelper.retBool(getMapper().deleteBatchIds(idList));
        if (result) {
            //刷新缓存
            //保存缓存
            list.stream().forEach(entity -> {
                //保存缓存字段
                refreshCacheFields(entity, BaseOperationEnum.DELETE_BATCH);
            });
            processBatchAfterOperation(list,BaseOperationEnum.DELETE_BATCH);
        }
        return result;
    }

    /**
     * 删除
     *
     * @param lambdaQueryWrapper
     */
    @Transactional(rollbackFor = Exception.class)
    default void delete(LambdaQueryWrapper<T> lambdaQueryWrapper) {
        getMapper().delete(lambdaQueryWrapper);
    }

    /**
     * 校验唯一
     *
     * @param entity   实体对象
     * @param isUpdate 是否是更新
     */
    default void checkUniqueField(T entity, boolean isUpdate) {
        //获取当前类的属性
        Field[] fields = entity.getClass().getDeclaredFields();

        //获取当前类父类的属性，先判断对应实体是否有id字段
        Field[] superFields = entity.getClass().getSuperclass().getDeclaredFields();

        Field[] allFields = ArrayUtils.addAll(fields, superFields);

        Optional<Field> idFiledOptional = Arrays.stream(allFields).filter(field -> field.isAnnotationPresent(TableId.class)).findFirst();
        //当不包含@TableId是，忽略
        if (!idFiledOptional.isPresent()) {
            return;
        }

        //主键字段
        Field idField = idFiledOptional.get();
        idField.setAccessible(true);
        //判断单一索引
        for (Field field : fields) {
            if (field.isAnnotationPresent(Unique.class)) {
                Unique unique = field.getDeclaredAnnotation(Unique.class);
                QueryWrapper<T> wrapper = Wrappers.query();
                Long integer;
                try {
                    Object value = getFieldValue(entity, field);
                    //如果没有指定列，默认是字段的驼峰转下划线
                    String column;
                    if (StringUtils.isBlank(unique.column())) {
                        //字段，驼峰转下划线
                        column = ReportUtils.camelToUnderline(field.getName());
                    } else {
                        column = unique.column();
                    }
                    wrapper.eq(column, value);
                    if (isUpdate) {
                        wrapper.ne(idField.getAnnotation(TableId.class).value(), idField.get(entity));
                    }
                    integer = getMapper().selectCount(wrapper);
                } catch (Exception e) {
                    continue;
                }
                if (integer > 0) {
                    throw BusinessExceptionBuilder.build(unique.code(), field.getName());
                }
            }
        }

        //判断联合索引
        //用户存放各分组的聚合索引
        Map<String, QueryWrapper<T>> unionUniqueMap = new HashMap<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(UnionUnique.class)) {
                try {
                    UnionUnique[] unionUniques = field.getDeclaredAnnotationsByType(UnionUnique.class);
                    for (UnionUnique unionUnique : unionUniques) {
                        String group = unionUnique.group();
                        Object value = getFieldValue(entity, field);
                        String column;
                        if (StringUtils.isBlank(unionUnique.column())) {
                            //字段，驼峰转下划线
                            column = ReportUtils.camelToUnderline(field.getName());
                        } else {
                            column = unionUnique.column();
                        }
                        if (unionUniqueMap.containsKey(group)) {
                            QueryWrapper<T> unionWrapper = unionUniqueMap.get(group);
                            unionWrapper.eq(column, value);
                        } else {
                            QueryWrapper<T> unionWrapper = Wrappers.query();
                            unionWrapper.eq(column, value);
                            unionUniqueMap.put(group, unionWrapper);
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }

        //遍历聚集索引
        Set<Map.Entry<String, QueryWrapper<T>>> entries = unionUniqueMap.entrySet();

        for (Map.Entry<String, QueryWrapper<T>> entry:entries) {
            QueryWrapper<T> queryWrapper = entry.getValue();
            if (isUpdate) {
                try {
                    queryWrapper.ne(idField.getAnnotation(TableId.class).value(), idField.get(entity));
                } catch (Exception e) {
                    return;
                }
            }
            //查询
            Long result = getMapper().selectCount(queryWrapper);

            if (result > 0) {
                String group = entry.getKey();
                //错误提示
                Class<? extends BaseEntity> aClass = entity.getClass();
                UnionUniqueCode[] unionUniqueCodes = aClass.getAnnotationsByType(UnionUniqueCode.class);

                for (UnionUniqueCode unionUniqueCode : unionUniqueCodes) {
                    if (StringUtils.equals(unionUniqueCode.group(), group)) {
                        throw BusinessExceptionBuilder.build(unionUniqueCode.code());
                    }
                }
            }
        }
    }

    /**
     * 获取属性值
     *
     * @param entity
     * @param field
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    default Object getFieldValue(T entity, Field field) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), entity.getClass());
        Method readMethod = propertyDescriptor.getReadMethod();
        return readMethod.invoke(entity);
    }

    /**
     * 根据指定字段查询对应的值
     *
     * @param column
     * @param value
     * @return
     */
    default List<T> list(String column, Object value) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column, value);
        return getMapper().selectList(queryWrapper);
    }

    /**
     * 查询数量
     *
     * @param wrapper
     * @return
     */
    default Long count(Wrapper<T> wrapper) {
        return getMapper().selectCount(wrapper);
    }

    /**
     * 根据指定条件查询对应的记录
     *
     * @param wrapper
     * @return
     */
    default List<T> list(Wrapper<T> wrapper) {
        return getMapper().selectList(wrapper);
    }

    /**
     * ResponseBean
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    default T getById(Serializable id) {
        return getMapper().selectById(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    default List<T> findAll() {
        return getMapper().selectList(Wrappers.emptyWrapper());
    }

    /**
     * 根据指定字段更新值
     *
     * @param id
     * @param column
     * @param value
     */
    default Integer updateColumn(Long id, String column, Object value) {
        Map<String, Object> params = new HashMap<>(1);
        params.put(column, value);
        return getMapper().updateFieldsById(params, id);
    }

    /**
     * 批量更新
     * @param map 指定字段和值
     * @param list 待更新的实体数据
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    default Integer updateFieldsBatch(Map<String, Object> map, List<T> list) {
        return getMapper().updateFieldsBatch(map, list);
    }
}
