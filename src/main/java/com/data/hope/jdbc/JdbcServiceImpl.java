package com.data.hope.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.data.hope.DruidProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description 数据源 测试方法
 * @author guoqinghua
 * @date 20220308
 **/
@Service
public class JdbcServiceImpl implements JdbcService {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(JdbcServiceImpl.class);

    private final DruidProperties druidProperties;

    /**
     * 所有数据源的连接池存在map里
     */
   public static Map<Long, DruidDataSource> poolMap = new ConcurrentHashMap<>();

    public JdbcServiceImpl(DruidProperties druidProperties) {
        this.druidProperties = druidProperties;
    }

    public DruidDataSource getJdbcConnectionPool(DataSourceDto dataSource) {
        if (poolMap.containsKey(dataSource.getId())) {
            return poolMap.get(dataSource.getId());
        } else {
            try {
                if (!poolMap.containsKey(dataSource.getId())) {
                    DruidDataSource pool = druidProperties.dataSource(dataSource.getJdbcUrl(),
                            dataSource.getUsername(), dataSource.getPassword(), dataSource.getDriverName());
                    poolMap.put(Long.valueOf(dataSource.getId()), pool);

                    logger.info("创建连接池成功：{}", dataSource.getJdbcUrl());
                }
                return poolMap.get(dataSource.getId());
            } finally {

            }
        }
    }


    /**
     * 删除数据库连接池
     *
     * @param id
     */
    @Override
    public void removeJdbcConnectionPool(Long id) {
        try {
            DruidDataSource pool = poolMap.get(id);
            if (pool != null) {
                logger.info("remove pool success, datasourceId:{}", id);
                poolMap.remove(id);
            }
        } catch (Exception e) {
            logger.error("error", e);
        } finally {

        }
    }

    /**
     * 获取连接
     *
     * @param dataSource
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getPooledConnection(DataSourceDto dataSource) throws SQLException{
        DruidDataSource pool = getJdbcConnectionPool(dataSource);
        return pool.getConnection();
    }

    /**
     * 测试数据库连接  获取一个连接
     *
     * @param dataSource
     * @return
     * @throws ClassNotFoundException driverName不正确
     * @throws SQLException
     */
    @Override
    public Connection getUnPooledConnection(DataSourceDto dataSource) throws SQLException {
        DruidDataSource druidDataSource = druidProperties.dataSource(dataSource.getJdbcUrl(),
                dataSource.getUsername(), dataSource.getPassword(), dataSource.getDriverName());
        return druidDataSource.getConnection();
    }

}
