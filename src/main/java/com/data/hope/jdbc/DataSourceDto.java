
package com.data.hope.jdbc;

import com.data.hope.curd.dto.ReportBaseDTO;

import java.io.Serializable;
import java.util.Map;


/**
*
* @description 数据源 dto
* @author guoqinghua
* @date 2022-03-08
**/

public class DataSourceDto extends ReportBaseDTO implements Serializable {
    /** 数据源编码 */
     private String sourceCode;

    /** 数据源名称 */
    private String sourceName;

    /** 数据源描述 */
     private String sourceDesc;

    /** 数据源类型 DIC_NAME=SOURCE_TYPE; mysql，orace，sqlserver，elasticsearch，接口，javaBean，数据源类型字典中item-extend动态生成表单 */
     private String sourceType;

    /** 数据源连接配置json：关系库{ jdbcUrl:'', username:'', password:'','driverName':''}ES-sql{ apiUrl:'http://127.0.0.1:9092/_xpack/sql?format=json','method':'POST','body':'{"query":"select 1"}' }  接口{ apiUrl:'http://ip:port/url', method:'' } javaBean{ beanNamw:'xxx' } */
     private String sourceConfig;

    /** 0--已禁用 1--已启用  DIC_NAME=ENABLE_FLAG */
     private Integer enableFlag;

    /** 0--未删除 1--已删除 DIC_NAME=DELETE_FLAG */
     private Integer deleteFlag;

    /**************************************************************/
    /**关系型数据库jdbcUrl */
    private String jdbcUrl;

    /** 关系型数据库用户名 */
    private String username;

    /** 关系型数据库密码 */
    private String password;

    /** 关系型数据库驱动类 */
    private String driverName;

    /** 关系型数据库sql */
    private String sql;

    /** http requestUrl */
    private String apiUrl;

    /** http method */
    private String method;

    /** http header */
    private String header;

    /** http 请求体 */
    private String body;

    /** 动态查询sql或者接口中的请求体 */
    private String dynSentence;

    /** 传入的自定义参数，解决url中存在的动态参数*/
    private Map<String, Object> contextData;


    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceDesc() {
        return sourceDesc;
    }

    public void setSourceDesc(String sourceDesc) {
        this.sourceDesc = sourceDesc;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceConfig() {
        return sourceConfig;
    }

    public void setSourceConfig(String sourceConfig) {
        this.sourceConfig = sourceConfig;
    }

    public Integer getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Integer enableFlag) {
        this.enableFlag = enableFlag;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDynSentence() {
        return dynSentence;
    }

    public void setDynSentence(String dynSentence) {
        this.dynSentence = dynSentence;
    }

    public Map<String, Object> getContextData() {
        return contextData;
    }

    public void setContextData(Map<String, Object> contextData) {
        this.contextData = contextData;
    }
}
