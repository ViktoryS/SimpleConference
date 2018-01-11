package controller.commands;

import controller.FrontCommand;
import exceptions.NullParatemerException;
import exceptions.WrongParameterException;
import model.entity.Role;
import model.entity.builders.UserBuilder;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import static model.dao.utils.constants.UserConstants.*;
import static utils.constants.PageConstants.*;
/**
 * Created by Vikki on 11.01.2018.
 */
public class Register extends FrontCommand {
    String PASSWORD_REP_ATTR = "passwordRepeat";
    String ROLE_ATTRIBUTE= "role";

    @Override
    public void process() throws ServletException, IOException {
        String message = null;
        String type = null;

        String rqName = request.getParameter(NAME);
        String rqLogin = request.getParameter(LOGIN);
        String rqPassword = request.getParameter(PASSWORD);
        String rqPasswordRepeat = request.getParameter(PASSWORD_REP_ATTR);
        String rqEmail = request.getParameter(EMAIL);
        String rqRole = request.getParameter(ROLE_ATTRIBUTE);

        UserService userService = ServiceFactory.getInstance().createUserService();
        try{
            userService.registration(new UserBuilder()
                    .setName(rqName)
                    .setLogin(rqLogin)
                    .setPassword(rqPassword)
                    .setEmail(rqEmail)
                    .setRole(Role.valueOf(rqRole))
                    .build(), rqPasswordRepeat);
            message = "Ok!";
            type = TYPE_SUCCESS;
        } catch (SQLException e) {
            message = "DB error!";
            type = TYPE_ERROR;
        } catch (NullParatemerException e){
            message = "Some parameter is null!";
            type = TYPE_ERROR;
        } catch (WrongParameterException e){
            message = "Wrong parameter: " + e.getMessage();
            type = TYPE_ERROR;
        }

        request.setAttribute(MESSAGE_ATTRIBUTE, message);
        request.setAttribute(TYPE_ATTRIBUTE, type);
        request.setAttribute(ROLES_ATTRIBUTE, Role.values());
        forward(REGISTER_PAGE);
    }
}
