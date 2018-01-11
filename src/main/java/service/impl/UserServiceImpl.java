package service.impl;

import exceptions.NullParatemerException;
import exceptions.WrongParameterException;
import model.dao.UserDao;
import model.dao.factory.DaoFactory;
import model.dao.utils.QueryBuilder;
import model.dao.utils.constants.UserConstants;
import model.entity.Role;
import model.entity.User;
import service.UserService;
import utils.ParameterUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vikki on 10.01.2018.
 */
public class UserServiceImpl implements UserService{

    private String NAME_REGEX = "^([A-Z][a-z]+)\\s[A-Z]$";
    private String LOGIN_PASSWORD_REGEX = "^[A-Za-z]\\w+";
    private String EMAIL_REGEX = "^([A-za-z]+)((\\.|\\+)?\\w+)*@[a-z]+(\\.\\w+)?\\.(ua|com|net|ru)$";

    private UserDao userDao;

    private static UserService userService = new UserServiceImpl();
    public static UserService getInstance(){
        return userService;
    }

    private UserServiceImpl(){
        userDao = DaoFactory.getInstance().createUserDao();
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws SQLException,
            NullParatemerException, WrongParameterException {
        String login = request.getParameter("j_username");
        String password = request.getParameter("j_password");
        ParameterUtils.checkNull(login, password);

        User user = null;
        for(User sUser: userDao.findAll()){
            if(sUser.getLogin().equals(login)){
                user = sUser;
                break;
            }
        }
        if(user != null) {
            if (user.getPassword().equals(password)) {
                Cookie cookie = new Cookie("user", "" + user.hashCode());
                response.addCookie(cookie);

                HttpSession session = request.getSession();
                session.setAttribute("user", user);
            }else {
                throw new WrongParameterException(password);
            }
        }else {
            throw new WrongParameterException(login);
        }
    }

    public void registration(User user, String passwordRep) throws NullParatemerException, SQLException, WrongParameterException {
        ParameterUtils.checkNull(user.getName(), user.getLogin(), user.getPassword(), user.getEmail());
        if(!user.getPassword().equals(passwordRep)) throw new WrongParameterException(passwordRep);

        if(checkRegex(user.getName(), NAME_REGEX) && checkRegex(user.getLogin(), LOGIN_PASSWORD_REGEX)
                && checkRegex(user.getPassword(), LOGIN_PASSWORD_REGEX) && checkRegex(user.getEmail(), EMAIL_REGEX)){
            userDao.create(user);
        }
    }

    public List<User> findByRole(Role role) throws SQLException{
        String query = QueryBuilder.findWithCondition(UserConstants.TABLE, UserConstants.ROLE_ID);
        return userDao.findByRole(role);
    }

    private boolean checkRegex(String parameter, String regex) throws WrongParameterException{
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(parameter);
        if(!matcher.matches())
            throw new WrongParameterException(parameter);
        return true;
    }
}
