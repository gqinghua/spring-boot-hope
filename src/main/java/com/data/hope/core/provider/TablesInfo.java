package com.data.hope.core.provider;

import java.io.Serializable;

/**
 * 数据库信息
 * @author 92306
 */

public class TablesInfo implements Serializable {

    /**
     * 数据库名称
     */
    private String name;


    /**
     * 注释信息
     */
    private String remarks;


    /**
     * 模式
     */
    private String tableSchema;

    public TablesInfo() {
    }

    public String getName() {
        return this.name;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setRemarks(final String remarks) {
        this.remarks = remarks;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }
}
