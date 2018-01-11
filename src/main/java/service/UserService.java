package service;

import exceptions.NullParatemerException;
import exceptions.WrongParameterException;
import model.entity.Role;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vikki on 10.01.2018.
 */
public interface UserService {
    void login(HttpServletRequest request, HttpServletResponse response) throws SQLException,
            NullParatemerException, WrongParameterException;
    void registration(User user, String passwordRep) throws NullParatemerException, SQLException, WrongParameterException;
    List<User> findByRole(Role role) throws SQLException;
}
