package com.data.hope.core.provider.ddl;


import java.util.List;

/**
 * @Classname DDLProvider
 * @Description DDL类型接口 标准
 * @Version 1.0.0
 * @Date 2022/9/27
 * @@author by guoqinghua
 */
public abstract class DDLProvider {
    /**
     * 创建视图
     * @param name
     * @param viewSQL
     * @return
     */
    public abstract String createView(String name, String viewSQL);

    /**
     * 删除表
     * @param name
     * @return
     */
    public abstract String dropTable(String name);

    /**
     * 删除视图
     * @param name
     * @return
     */
    public abstract String dropView(String name);

    /**
     * 生成表
     * @param name
     * @return
     */
    public abstract String replaceTable(String name);
    /**
     * 生成建表sql
     * @param name
     * @return
     */
    public abstract String createTableSql(String name, List<Object> datasetTableFields, Object engine);
    /**
     * 插入数据
     * @param name
     * @return
     */
    public abstract String insertSql(String name, List<String[]> dataList);
}
