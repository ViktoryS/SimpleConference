package model.dao;

import model.entity.Address;
import model.entity.Event;
import model.entity.Lecture;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vikki on 23.12.2017.
 */
public interface EventDao extends GenericDao<Event> {
    List<Event> findByUser(User user) throws SQLException;
    List<Event> findByLecture(Lecture lecture) throws SQLException;
    List<Event> findByAddress(Address address) throws SQLException;
}
