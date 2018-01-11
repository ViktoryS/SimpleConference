package model.entity.proxy;

import model.dao.EventDao;
import model.dao.UserDao;
import model.dao.factory.DaoFactory;
import model.entity.Event;
import model.entity.Lecture;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vikki on 23.12.2017.
 */
public class LectureProxy extends Lecture {

    @Override
    public User getSpiker() {
        if(super.getSpiker() != null) return super.getSpiker();
        UserDao userDao = DaoFactory.getInstance().createUserDao();
        try {
            return userDao.findByLecture(this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Event> getEvents() {
        if(super.getEvents() != null) return super.getEvents();
        EventDao eventDao = DaoFactory.getInstance().createEventDao();
        try{
            return eventDao.findByLecture(this);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
