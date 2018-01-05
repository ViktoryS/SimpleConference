package model.entity.proxy;

import model.dao.EventDao;
import model.dao.LectureDao;
import model.dao.factory.DaoFactory;
import model.entity.Event;
import model.entity.Lecture;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vikki on 23.12.2017.
 */
public class UserProxy extends User {
    @Override
    public List<Lecture> getLectures() {
        if(super.getLectures() != null) return super.getLectures();
        LectureDao lectureDao = DaoFactory.getInstance().createLectureDao();
        try{
            return lectureDao.findAllByItem(this);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Event> getEvents() {
        if(super.getEvents() != null) return super.getEvents();
        EventDao eventDao = DaoFactory.getInstance().createEventDao();
        try{
            return eventDao.findAllByItem(this);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
