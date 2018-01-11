package model.dao;

import model.entity.Event;
import model.entity.Lecture;
import model.entity.Role;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vikki on 23.12.2017.
 */
public interface UserDao extends GenericDao<User> {
    List<Event.RegisteredUser> findAllByItem(Event event) throws SQLException;
    User findByLecture(Lecture lecture) throws SQLException;
    List<User> findByRole(Role role) throws SQLException;
}
