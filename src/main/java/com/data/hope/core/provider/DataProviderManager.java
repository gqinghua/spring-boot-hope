

package com.data.hope.core.provider;

import com.data.hope.core.entity.TableField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * @Classname DataProviderManager
 * @Description jdbc 标准实现
 * @Version 1.0.0
 * @Date 2022/9/27
 * @@author by guoqinghua
 */
public interface DataProviderManager {
    /**
     * 获取缓存数据
     *
     * @return
     */
    List<DataProviderInfo> getSupportedDataProviders();


    /**
     * 数据源模板
     *
     * @param type
     * @return
     * @throws IOException
     */
    DataProviderConfigTemplate getSourceConfigTemplate(String type) throws IOException;

    /**
     * 数据测试及获取数据
     *
     * @param source
     * @return
     * @throws Exception
     */
    Object testConnection(DataProviderSource source) throws Exception;

    /**
     * 获取数据库信息
     *
     * @param source
     * @return
     * @throws SQLException
     */
    Set<String> readAllDatabases(DataProviderSource source) throws SQLException;

    /**
     * 获取当前数据库信息
     *
     * @param source
     * @return
     * @throws SQLException
     */
    Set<String> readDatabases(DataProviderSource source) throws SQLException;

    /**
     * 获取数据库表信息
     *
     * @param source
     * @param
     * @return
     * @throws SQLException
     */
    Set<String> readTableDatabase(DataProviderSource source) throws SQLException;


    /**
     * 获取所有的表
     *
     * @param source
     * @param database
     * @return
     * @throws SQLException
     */
    Object readTables(DataProviderSource source, String database) throws SQLException;

    /**
     * 获取数据库字段
     *
     * @param source
     * @param schema
     * @param table
     * @return
     * @throws SQLException
     */
    Set<Column> readTableColumns(DataProviderSource source, String schema, String table) throws SQLException;

    /**
     * 执行
     *
     * @param source
     * @param queryScript
     * @param param
     * @return
     * @throws Exception
     */
    Dataframe execute(DataProviderSource source, QueryScript queryScript, ExecuteParam param) throws Exception;

    /**
     * 已支持的标准函数
     *
     * @return 支持的函数名称列表
     */
    Set<StdSqlOperator> supportedStdFunctions(DataProviderSource source);


    /**
     * 已支持的标准函数
     *
     * @return 支持的函数名称列表
     */
    boolean validateFunction(DataProviderSource source, String snippet);

    /**
     * 修改数据
     *
     * @param source
     */
    void updateSource(DataProviderSource source);


     void execDDL(DataProviderSource source, QueryScript script, ExecuteParam executeParam) throws SQLException;


     List<TableField> fetchResultField(DataProviderSource source,QueryScript script, ExecuteParam executeParam) throws Exception ;


}