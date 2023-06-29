package com.data.hope.core.provider.sql;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * sql 字段数据
 * @author guoqinghua
 * @since 2022-08-16
 */
public abstract class ColumnOperator implements Operator {

    public String column;

    public String tableName;


    public String getColumnKey() {
        if (column == null) {
            return null;
        }
        return String.join(".", column);
    }


    public String[] getColumnNames(boolean withDefaultColumnPrefix, String defaultColumnPrefix) {
        if (withDefaultColumnPrefix) {
            String[] names = new String[column.split(",").length + 1];
            names[0] = defaultColumnPrefix;
            return (String[]) ArrayUtils.add(column.split(","), 0, defaultColumnPrefix);
        } else {
            if (StringUtils.isNotBlank(tableName)){
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(tableName);
                arrayList.add(column);
                String[] aliArrayList = arrayList.stream().toArray(String[]::new);
                return aliArrayList;
            }else {
                return column.split(",");
            }

        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ColumnOperator)) {
            return false;
        }
        ColumnOperator that = (ColumnOperator) o;
        return Arrays.equals(column.split(","), that.column.split(","));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(column.split(","));
    }



    public void setColumn(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
