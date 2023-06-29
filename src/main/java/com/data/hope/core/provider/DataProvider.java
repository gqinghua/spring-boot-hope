

package com.data.hope.core.provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.data.hope.core.base.AutoCloseBean;
import com.data.hope.core.entity.TableField;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @Classname DataProvider
 * @Description 数据读取标准实现
 * @Version 1.0.0
 * @Date 2022/9/27
 * @@author by guoqinghua
 */
public abstract class DataProvider extends AutoCloseBean {

    static ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public DataProviderInfo getBaseInfo() throws IOException {
        DataProviderConfigTemplate template = getConfigTemplate();
        DataProviderInfo dataProviderInfo = new DataProviderInfo();
        dataProviderInfo.setName(template.getName());
        dataProviderInfo.setType(template.getType());
        return dataProviderInfo;
    }

    /**
     * 测试DataProvider的连接。连接成功返回true，否则返回false。
     * <p>
     * Test the connection to the data source.Returns true on success of the connection or false on failure.
     *
     * @param source 一个Json字符串
     * @return Returns true on success of the connection or false on failure.
     */
    public abstract Object test(DataProviderSource source) throws Exception;

    /**
     * 获取数据源
     *
     * @param source
     * @return
     * @throws SQLException
     */
    public abstract Set<String> readAllDatabases(DataProviderSource source) throws SQLException;

    /**
     * 读取所有的数据库
     *
     * @param source
     * @return
     * @throws SQLException
     */
    public abstract Set<String> readDatabases(DataProviderSource source) throws SQLException;

    /**
     * 获取数据表
     *
     * @param
     * @param source
     * @return
     * @throws SQLException
     */
    public abstract Object readTables(DataProviderSource source, String database) throws SQLException;

    /**
     * 获取所有的库
     *
     * @param source
     * @param
     * @param
     * @return
     * @throws SQLException
     */

    public abstract Set<String> readTableDatabase(DataProviderSource source) throws SQLException;

    /**
     * 获取字段名称
     *
     * @param source
     * @param
     * @param
     * @return
     * @throws SQLException
     */
    public abstract Set<Column> readTableColumns(DataProviderSource source, String schema, String table) throws SQLException;

    /**
     * 读取DataProvider的配置模板，配置模板的信息是创建这个DataProvider实例时所需的信息。
     * <p>
     * Read the configuration template of the DataProvider. The configuration template information is needed to create the instance of the DataProvider.
     * This template is configured by the DataProvider according to the information it needs and stored in JSON format.The default save path is classpath:/data-provider.json
     *
     * @return 配置模板
     * @throws IOException 配置文件不存在或格式错误时抛出异常
     */
    public DataProviderConfigTemplate getConfigTemplate() throws IOException {
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(getConfigFile())) {
            return MAPPER.readValue(resourceAsStream, DataProviderConfigTemplate.class);
        }
    }


    /**
     * 执行ddl语句
     * @param source
     * @param script
     * @param executeParam
     * @throws SQLException
     */
    public abstract void execDDL(DataProviderSource source, QueryScript script, ExecuteParam executeParam) throws SQLException;


    public abstract String getConfigDisplayName(String name);

    public abstract String getConfigDescription(String name);

    /**
     * 加密
     *
     * @param config
     * @param script
     * @param executeParam
     * @return
     * @throws Exception
     */
    public abstract String getQueryKey(DataProviderSource config, QueryScript script, ExecuteParam executeParam) throws Exception;


    /**
     * 查询sql执行
     *
     * @param config
     * @param script
     * @param executeParam
     * @return
     * @throws Exception
     */
    public abstract Dataframe execute(DataProviderSource config, QueryScript script, ExecuteParam executeParam) throws Exception;

    /**
     * 返回DataProvider的type，type的值由实现者定义。
     * 这个type值作为DataProvider的唯一标识，必须是全局唯一的。
     * <p>
     * Returns the dataProvider's type.The value of type is defined by the implementation.
     * This type, as the unique identifier of the DataProvider, must be globally unique.
     *
     * @return dataProvider's type
     */
    public String getType() throws IOException {
        return getBaseInfo().getType();
    }

    /**
     * 获取模板信息
     * @return
     */
    public abstract String getConfigFile();

    /**
     * 获取Integer最大
     * @return
     */
    @Override
    public int timeoutMillis() {
        return Integer.MAX_VALUE;
    }


    /**
     * 已支持的标准函数
     *
     * @return 支持的函数名称列表
     */
    public Set<StdSqlOperator> supportedStdFunctions(DataProviderSource source) {
        return Collections.emptySet();
    }

    /**
     * 创建模板 func
     * @param source
     * @param snippet
     * @return
     */
    public abstract boolean validateFunction(DataProviderSource source, String snippet);

    /**
     * 数据源被修改，重置数据源缓存
     *
     * @param source
     */
    public  abstract void resetSource(DataProviderSource source);


    /**
     * 获取filed 数据
     * @param
     * @return
     * @throws Exception
     */
    public abstract List<TableField> fetchResultField( DataProviderSource source, QueryScript script, ExecuteParam executeParam ) throws Exception ;



}