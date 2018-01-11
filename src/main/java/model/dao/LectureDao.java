package model.dao;

import model.entity.Event;
import model.entity.Lecture;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vikki on 23.12.2017.
 */
public interface LectureDao extends GenericDao<Lecture> {
    Lecture findByEvent(Event event) throws SQLException;
    List<Lecture> findByUser(User user) throws SQLException;
}
