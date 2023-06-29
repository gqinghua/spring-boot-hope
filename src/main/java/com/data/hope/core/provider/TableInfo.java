package com.data.hope.core.provider;

import java.util.List;
import java.util.Set;

/**
 * @program: report
 * @ClassName TableInfo
 * @description: [用一句话描述此类]
 * @author: GUO
 * @create: 2022-08-09 15:26
 **/

public class TableInfo {


    private  TablesInfo tableName;

    private List<String> primaryKeys;

    private Set<Column> columns;


    public TablesInfo getTableName() {
        return tableName;
    }

    public void setTableName(TablesInfo tableName) {
        this.tableName = tableName;
    }

    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(List<String> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public Set<Column> getColumns() {
        return columns;
    }

    public void setColumns(Set<Column> columns) {
        this.columns = columns;
    }
}
