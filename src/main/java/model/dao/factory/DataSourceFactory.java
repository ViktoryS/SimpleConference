package model.dao.factory;

import javax.sql.DataSource;

public class DataSourceFactory {
    private DataSource dataSource;

    private DataSourceFactory(){
        dataSource = ConnectionPool.getDataSource();
    }

    public static DataSourceFactory getInstanse(){
        return initDataSourceFactory();
    }

    private static DataSourceFactory initDataSourceFactory(){
        return new DataSourceFactory();
    }

    public DataSource getDataSource(){
        return dataSource;
    }
}
