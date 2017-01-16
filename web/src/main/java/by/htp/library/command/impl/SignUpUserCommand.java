package by.htp.library.command.impl;


import by.htp.library.command.AttributeName;
import by.htp.library.command.ICommand;
import by.htp.library.command.PageName;
import by.htp.library.command.exception.CommandException;
import by.htp.library.controller.Controller;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.impl.UserService;
import by.htp.library.util.CreateErrorMessage;
import by.htp.library.validation.Validation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignUpUserCommand implements ICommand {
    private static Logger logger = LogManager.getLogger(SignUpUserCommand.class);
    private final static String LOGIN_EXISTS = "locale.error.login.exists";
    private final static String SUCCESS_OPERATION = "locale.message.success.registration";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        String registrName = request.getParameter(AttributeName.REGISTR_NAME);
        String registrLogin = request.getParameter(AttributeName.REGISTR_LOGIN);
        String registrPass = request.getParameter(AttributeName.REGISTR_PASS);
        String registrRepeatPass = request.getParameter(AttributeName.REGISTR_REPEAT_PASS);
        String registrEmail = request.getParameter(AttributeName.REGISTR_EMAIL);
        String userLocale = (String) session.getAttribute(AttributeName.LOCALE);
        String message = Validation.getInstance().validateData(registrName, registrLogin, registrPass, registrRepeatPass, registrEmail, userLocale);
        if (message != null) {
            request.setAttribute(AttributeName.INVALID_REGISTR_DATA, message);
            return PageName.AUTHORIZATION;
        }
        try {
            if (UserService.getInstance().matchExistLogin(registrLogin)) {
                message = CreateErrorMessage.createErrorMessage(LOGIN_EXISTS, userLocale);
                request.setAttribute(AttributeName.INVALID_REGISTR_DATA, message);
            }else {
                UserService.getInstance().checkRegister(registrName, registrLogin, registrPass, registrEmail);
                message = CreateErrorMessage.createErrorMessage(SUCCESS_OPERATION, userLocale);
                request.setAttribute(AttributeName.SUCCESS_OPERATION, message);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PageName.AUTHORIZATION;
    }



}