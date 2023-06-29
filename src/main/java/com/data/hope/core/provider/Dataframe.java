

package com.data.hope.core.provider;


import com.data.hope.core.base.PageInfo;
import com.data.hope.core.common.UUIDGenerator;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;


/** 返回数据
 * @author 92306
 */

public class Dataframe implements Serializable {

    private final String id;

    /**
     * 名称
     */
    private String name;


    /**
     * 列信息
     */
    private List<Column> columns;

    /**
     * 数据
     */
    private List<List<Object>> data;

    /**
     * 分页
     */
    private PageInfo pageInfo;

    /**
     * 脚本
     */
    private String script;
    /**
     * 数据库表名称
     */
    private String tablesName;

    private List<List<Object>> rows;

    public Dataframe() {
        this.id = "DF" + UUIDGenerator.generate();

    }

    public Dataframe(String id) {
        this.id = id;
    }

    public static Dataframe empty() {
        Dataframe dataframe = new Dataframe();
        dataframe.setColumns(Collections.emptyList());
        dataframe.setData(Collections.emptyList());
        return dataframe;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<List<Object>> getData() {
        return data;
    }

    public void setData(List<List<Object>> data) {
        this.data = data;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getTablesName() {
        return tablesName;
    }

    public void setTablesName(String tablesName) {
        this.tablesName = tablesName;
    }

    public List<List<Object>> getRows() {
        return rows;
    }

    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }
}