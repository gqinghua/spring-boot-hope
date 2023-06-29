package com.data.hope.constant;

/**
 * @program: report
 * @ClassName DatasourceTypes
 * @description: [用一句话描述此类]
 * @author: GUO
 * @create: 2022-06-09 11:42
 **/
public enum DatasourceTypes {
    excel("excel", "excel", "", "", "", "", ""),
    FILE("FILE", "FILE", "", "", "", "", ""),
    HTTP("HTTP", "HTTP", "", "", "", "", ""),
    mysql("mysql", "mysql", "com.mysql.jdbc.Driver", "`", "`", "", ""),
    TiDB("TiDB", "TiDB", "com.mysql.jdbc.Driver", "`", "`", "", ""),
    hive("hive", "hive", "org.apache.hive.jdbc.HiveDriver", "`", "`", "'", "'"),
    impala("impala", "impala", "org.apache.hive.jdbc.HiveDriver", "`", "`", "'", "'"),
    mariadb("mariadb", "mariadb", "com.mysql.jdbc.Driver", "`", "`", "'", "'"),
    StarRocks("StarRocks", "StarRocks", "com.mysql.jdbc.Driver", "`", "`", "'", "'"),
    ds_doris("ds_doris", "ds_doris", "com.mysql.jdbc.Driver", "`", "`", "'", "'"),
    pg("pg", "pg", "org.postgresql.Driver", "\"", "\"", "\"", "\""),
    sqlServer("sqlServer", "sqlServer", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "\"", "\"", "\"", "\""),
    oracle("oracle", "oracle", "oracle.jdbc.driver.OracleDriver", "\"", "\"", "\"", "\""),
    mongo("mongo", "mongodb", "com.mongodb.jdbc.MongoDriver", "`", "`", "\"", "\""),
    ck("ch", "ch", "ru.yandex.clickhouse.ClickHouseDriver", "`", "`", "'", "'"),
    db2("db2", "db2", "com.ibm.db2.jcc.DB2Driver", "\"", "\"", "\"", "\""),
    es("es", "es", "", "\"", "\"", "\"", "\""),
    redshift("redshift", "redshift", "org.postgresql.Driver", "\"", "\"", "\"", "\""),
    api("api", "api", "", "\"", "\"", "\"", "\""),
    engine_doris("engine_doris", "engine_doris", "com.mysql.jdbc.Driver", "`", "`", "", ""),
    engine_mysql("mysql", "mysql", "com.mysql.jdbc.Driver", "`", "`", "", "");

    private String feature;
    private String desc;
    private String driver;
    private String keywordPrefix;
    private String keywordSuffix;
    private String aliasPrefix;
    private String aliasSuffix;

    private DatasourceTypes(String feature, String desc, String driver, String keywordPrefix, String keywordSuffix, String aliasPrefix, String aliasSuffix) {
        this.feature = feature;
        this.desc = desc;
        this.driver = driver;
        this.keywordPrefix = keywordPrefix;
        this.keywordSuffix = keywordSuffix;
        this.aliasPrefix = aliasPrefix;
        this.aliasSuffix = aliasSuffix;
    }

    public String getFeature() {
        return this.feature;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getDriver() {
        return this.driver;
    }

    public String getKeywordPrefix() {
        return this.keywordPrefix;
    }

    public String getKeywordSuffix() {
        return this.keywordSuffix;
    }

    public String getAliasPrefix() {
        return this.aliasPrefix;
    }

    public String getAliasSuffix() {
        return this.aliasSuffix;
    }
}
