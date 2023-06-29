package com.data.hope.core.provider.sql;


/** 字段
 * @author 92306
 */
public abstract class ColumnOperatorCopy implements Operator {

    protected String column;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}
