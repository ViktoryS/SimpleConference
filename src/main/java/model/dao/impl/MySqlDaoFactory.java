package model.dao.impl;

import model.dao.AddressDao;
import model.dao.EventDao;
import model.dao.LectureDao;
import model.dao.UserDao;
import model.dao.factory.DaoFactory;
import model.dao.factory.DataSourceFactory;

import javax.sql.DataSource;

/**
 * Created by Vikki on 04.01.2018.
 */
public class MySqlDaoFactory extends DaoFactory {
    private DataSource dataSource;

    public MySqlDaoFactory(){
        dataSource = DataSourceFactory.getInstanse().getDataSource();
    }

    @Override
    public UserDao createUserDao() {
        return new MySqlUserDao(dataSource);
    }

    @Override
    public LectureDao createLectureDao() {
        return new MySqlLectureDao(dataSource);
    }

    @Override
    public EventDao createEventDao() {
        return new MySqlEventDao(dataSource);
    }

    @Override
    public AddressDao createAddressDao() {
        return new MySqlAddressDao(dataSource);
    }
}
