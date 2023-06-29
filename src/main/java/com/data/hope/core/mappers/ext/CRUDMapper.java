

package com.data.hope.core.mappers.ext;

import com.data.hope.core.common.ReflectUtils;
import com.data.hope.core.entity.BaseEntity;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.StringJoiner;

public interface CRUDMapper extends BaseMapper {

    int deleteByPrimaryKey(String id);

    int insert(BaseEntity record);

    BaseEntity selectByPrimaryKey(String id);

    int updateByPrimaryKey(BaseEntity record);

    int updateByPrimaryKeySelective(BaseEntity record);

    default boolean exists(String id) {
        SQL sql = new SQL();
        sql.SELECT("COUNT(*)").FROM(getTableName()).WHERE("id='" + StringEscapeUtils.escapeSql(id) + "'");
        Long count = executeQuery(sql.toString());
        return count > 0;
    }

    default boolean checkUnique(BaseEntity entity) {
        SQL sql = new SQL();
        sql.SELECT("COUNT(*)").FROM(format(entity.getClass().getSimpleName()));
        Map<Field, Object> fields = ReflectUtils.getNotNullFields(entity);
        if (CollectionUtils.isEmpty(fields)) {
            return false;
        }
        for (Map.Entry<Field, Object> entry : fields.entrySet()) {
            sql.WHERE(String.format("`%s` = '%s'", format(entry.getKey().getName()),
                    StringEscapeUtils.escapeSql(entry.getValue().toString())));
        }
        long count = executeQuery(sql.toString());
        return count == 0;
    }

    default int deleteByPrimaryKeys(Class<?> c, Collection<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        SQL sql = new SQL();
        StringJoiner stringJoiner = new StringJoiner(",", "(", ")");
        for (String id : ids) {
            stringJoiner.add("'" + StringEscapeUtils.escapeSql(id) + "'");
        }
        sql.DELETE_FROM(format(c.getSimpleName()))
                .WHERE("`id` IN " + stringJoiner);
        return executeDelete(sql.toString());
    }

    default String getTableName() {
        Type mapperExt = this.getClass().getGenericInterfaces()[0];
        String[] split = mapperExt.getTypeName().split("\\.");
        String entityName = split[split.length - 1].replace("MapperExt", "");
        return format(entityName);
    }

    default String format(String field) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field);
    }

}