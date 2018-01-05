package model.dao;

import model.entity.Event;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vikki on 23.12.2017.
 */
public interface UserDao extends GenericDao<User> {
    List<Event.RegisteredUser> findAllByItem(Event event) throws SQLException;
}
