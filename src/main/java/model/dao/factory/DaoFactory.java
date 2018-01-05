package model.dao.factory;

import model.dao.AddressDao;
import model.dao.EventDao;
import model.dao.LectureDao;
import model.dao.UserDao;
import model.dao.impl.MySqlDaoFactory;

/**
 * Created by Vikki on 03.01.2018.
 */
public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract LectureDao createLectureDao();
    public abstract EventDao createEventDao();
    public abstract AddressDao createAddressDao();

    public static DaoFactory getInstance(){
        if (daoFactory != null) return daoFactory;
        else return daoFactory = new MySqlDaoFactory();
    }
}
