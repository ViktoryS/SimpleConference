package controller.commands;

import controller.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;

import static utils.constants.PageConstants.HOME_PAGE;

/**
 * Created by Vikki on 13.12.2017.
 */
public class Command extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        forward(HOME_PAGE);
    }
}
