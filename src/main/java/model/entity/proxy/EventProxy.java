package model.entity.proxy;

import model.dao.AddressDao;
import model.dao.LectureDao;
import model.dao.UserDao;
import model.dao.factory.DaoFactory;
import model.entity.Address;
import model.entity.Event;
import model.entity.Lecture;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vikki on 30.12.2017.
 */
public class EventProxy extends Event {

    @Override
    public Address getAddress(){
        if(super.getAddress() != null) return super.getAddress();
        AddressDao addressDao = DaoFactory.getInstance().createAddressDao();
        try {
            return addressDao.findByEntity(this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Lecture getLecture(){
        if(super.getLecture() != null) return super.getLecture();
        LectureDao lectureDao = DaoFactory.getInstance().createLectureDao();
        try {
            return lectureDao.findByEntity(this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<RegisteredUser> getRegisteredUsers() {
        if(super.getRegisteredUsers() != null && super.getRegisteredUsers().size() != 0)
            return super.getRegisteredUsers();
        UserDao userDao = DaoFactory.getInstance().createUserDao();
        try {
            return userDao.findAllByItem(this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
