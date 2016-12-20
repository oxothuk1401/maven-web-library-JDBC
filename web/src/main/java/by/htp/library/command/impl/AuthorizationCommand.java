package by.htp.library.command.impl;

import by.htp.library.command.AttributeName;
import by.htp.library.command.ICommand;
import by.htp.library.command.PageName;
import by.htp.library.controller.exception.CommandException;
import by.htp.library.entity.User;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.validation.Validation;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthorizationCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String login = request.getParameter(AttributeName.LOGIN);
        String password = request.getParameter(AttributeName.PASSWORD);
        HttpSession session = request.getSession();
        if (!validateData(login, password)) {
            request.setAttribute(AttributeName.INVALID_DATA, true);
            return PageName.AUTHORIZATION;
        }
        try {
            User user = UserService.getInstance().authorizeUser(login, password);
            if (user != null) {
                session.setAttribute(AttributeName.USER_ID, user.getUserId());
                session.setAttribute(AttributeName.USER_NAME, user.getName());
                session.setAttribute(AttributeName.USER_ROLE, user.getRole().toString().toLowerCase());
                session.setAttribute(AttributeName.BLACKLIST, user.getBlacklist());
                session.setAttribute(AttributeName.LAST_PAGE, PageName.USER_PAGE);
                int pageUnique = new Random().nextInt();
                session.setAttribute(AttributeName.PAGE_UNIQUE, pageUnique);
                return PageName.USER_PAGE;
            } else {
                request.setAttribute(AttributeName.INVALID_DATA, true);
                return PageName.AUTHORIZATION;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    private boolean validateData(String login, String password) {
        Validation validation = Validation.getInstance();
        return validation.validateLogin(login) && validation.validatePassword(password);
    }
}
