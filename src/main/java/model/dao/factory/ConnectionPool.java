package model.dao.factory;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionPool {
    private static volatile DataSource dataSource;

    private ConnectionPool() {
    }

    public static DataSource getDataSource() {
        return initDataSource();
    }

    private static synchronized DataSource initDataSource() {
        if (dataSource == null) {
            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setUrl("jdbc:mysql://localhost:3306/flowerdb");
            basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            basicDataSource.setUsername("admin");
            basicDataSource.setPassword("admin");
            basicDataSource.setMinIdle(5);
            basicDataSource.setMaxIdle(10);
            basicDataSource.setMaxOpenPreparedStatements(100);
            return dataSource = basicDataSource;
        }else{
            return dataSource;
        }
    }
}
