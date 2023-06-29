package com.data.hope.curd.mapper.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 批量更新指定字段
 * @author guoqinghua
 * @since 2022-03-01
 */
public class UpdateFieldsBatchById extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        final String sql = "<script>UPDATE %s SET %s WHERE id in %s</script>";

        String tableName = tableInfo.getTableName();

        String updateSql = prepareUpdateSql();
        String whereSql = prepareWhereSql();

        String formatSql = String.format(sql, tableName, updateSql, whereSql);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, formatSql, modelClass);
        return this.addInsertMappedStatement(mapperClass, modelClass, "updateFieldsBatchById", sqlSource, new NoKeyGenerator(), null, null);
    }

    /**
     * 组装需要更新的sql
     * @return
     */
    private String prepareUpdateSql() {
        final StringBuilder valueSql = new StringBuilder();
        valueSql.append("<foreach collection=\"map\" item=\"value\" index=\"key\"  separator=\",\">");
        valueSql.append("${key} = #{value}");
        valueSql.append("</foreach>");
        return valueSql.toString();
    }

    /**
     * 拼接ID的where条件
     * @return
     */
    private String prepareWhereSql() {
        final StringBuilder valueSql = new StringBuilder();
        valueSql.append("<foreach collection=\"ids\" item=\"id\" open=\"(\" separator=\",\" close=\")\">");
        valueSql.append("#{id}");
        valueSql.append("</foreach>");
        return valueSql.toString();
    }


}
