
package com.data.hope.core.provider;

/**
 * @Classname ForeignKey
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/9/26
 * @@author by guoqinghua
 */

public class ForeignKey {

    private String database;

    private String table;

    private String column;


    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}
