package com.data.hope.jdbc;



import java.sql.Connection;
import java.sql.SQLException;

/**
 * @description 数据源 连接池
 * @author guoqinghua
 * @date 20220308
 **/
public interface JdbcService {

    /**
     * 删除数据库连接池
     *
     * @param id
     */
    void removeJdbcConnectionPool(Long id);


    /**
     * 获取连接
     *
     * @param dataSource
     * @return
     * @throws SQLException
     */
    Connection getPooledConnection(DataSourceDto dataSource) throws SQLException;

    /**
     * 测试数据库连接  获取一个连接
     *
     * @param dataSource
     * @return
     * @throws ClassNotFoundException driverName不正确
     * @throws SQLException
     */
    Connection getUnPooledConnection(DataSourceDto dataSource) throws SQLException;
}
