

package com.data.hope.core.provider;

import com.data.hope.core.base.consts.ValueType;

import java.io.Serializable;
import java.util.List;

/**
 * 列信息
 *
 * @author 92306
 */
public class Column implements Serializable {

    /**
     * 列名称
     */
    private String name;
    /**
     * 列类型
     */
    private ValueType type;

    /**
     * 格式化
     */
    private String fmt;

    /**
     * 外关联
     */
    private String pkDatabase;
    /**
     * 外关联
     */
    private String pkTable;
    /**
     * 外关联
     */
    private String pkColumn;

    /**
     * 注释信息
     */
    private String remarks;

    /**
     * 来源于表
     */
    private String tableInfo;

    /**
     * 是否隐藏
     */
    private String hidden = "true";

    /**
     * 表数据信息
     */
    private List<ForeignKey> foreignKeys;

    /**
     * 所属模式
     */
    private String schemaName;
    /**
     * 所属库
     */
    private String catalogName;

    /**
     * //java.sql.Types类型名称(列类型名称)
     */
    private String dataTypeName;
    /**
     * 列大小
     */
    private String columnSize;

    public Column(String name, ValueType type) {
        this.name = name;
        this.type = type;
    }

    public Column(String name, ValueType type, String tableInfo) {
        this.name = name;
        this.type = type;
        this.tableInfo = tableInfo;
    }

    public Column(String name, ValueType type, String tableInfo, String schemaName, String catalogName) {
        this.name = name;
        this.type = type;
        this.tableInfo = tableInfo;
        this.schemaName = schemaName;
        this.catalogName = catalogName;
    }

    public Column(String name, ValueType type,
                  String remarks,
                  String tableInfo, String schemaName, String catalogName) {
        this.name = name;
        this.type = type;
        this.tableInfo = tableInfo;
        this.schemaName = schemaName;
        this.remarks = remarks;
        this.catalogName = catalogName;
    }

    public Column() {
    }

    public static Column of(ValueType type, String names) {
        return new Column(names, type);
    }

    public static Column of(ValueType type, String names,
                            String remarks,  String databaseInfo,String schemaName, String catalogName
    ) {
        return new Column(names, type, remarks,databaseInfo, schemaName, catalogName);
    }

    public String columnName() {
        return name;
    }

    public String tableName() {

        return name;

    }

    public String columnKey() {
        return String.join(".", name);
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public ValueType getType() {
        return type;
    }

    public void setType(ValueType type) {
        this.type = type;
    }

    public String getFmt() {
        return fmt;
    }

    public void setFmt(String fmt) {
        this.fmt = fmt;
    }

    public String getPkDatabase() {
        return pkDatabase;
    }

    public void setPkDatabase(String pkDatabase) {
        this.pkDatabase = pkDatabase;
    }

    public String getPkTable() {
        return pkTable;
    }

    public void setPkTable(String pkTable) {
        this.pkTable = pkTable;
    }

    public String getPkColumn() {
        return pkColumn;
    }

    public void setPkColumn(String pkColumn) {
        this.pkColumn = pkColumn;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(String tableInfo) {
        this.tableInfo = tableInfo;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public List<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(List<ForeignKey> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public String getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(String columnSize) {
        this.columnSize = columnSize;
    }
}

