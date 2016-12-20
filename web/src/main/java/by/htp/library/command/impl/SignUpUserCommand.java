package by.htp.library.command.impl;


import by.htp.library.command.AttributeName;
import by.htp.library.command.ICommand;
import by.htp.library.command.PageName;
import by.htp.library.controller.exception.CommandException;
import by.htp.library.entity.User;
import by.htp.library.entity.UserRole;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.utils.CreateErrorMessage;
import by.htp.library.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class SignUpUserCommand implements ICommand {
	private final static String LOGIN_EXISTS = "locale.error.login.exists";
	private final static String INCORRECT_NAME = "locale.error.incorrect.name";
	private final static String INCORRECT_LOGIN = "locale.error.incorrect.login";
	private final static String INCORRECT_PASSWORD = "locale.error.incorrect.password";
	private final static String PASSWORDS_DONT_MATCH = "locale.error.pass.dont.match";
	private final static String INCORRECT_IMAIL = "locale.error.incorrect.email";
	User user;
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
			HttpSession session = request.getSession();
		String registrName = request.getParameter(AttributeName.REGISTR_NAME);
		String registrLogin = request.getParameter(AttributeName.REGISTR_LOGIN);
		String registrPass = request.getParameter(AttributeName.REGISTR_PASS);
		String registrRepeatPass = request.getParameter(AttributeName.REGISTR_REPEAT_PASS);
		String registrEmail = request.getParameter(AttributeName.REGISTR_EMAIL);
		String userLocale = (String) session.getAttribute(AttributeName.LOCALE);
		String errorMessage = validateData(registrName, registrLogin, registrPass, registrRepeatPass, registrEmail, userLocale);
		if (errorMessage != null) {
			request.setAttribute(AttributeName.INVALID_REGISTR_DATA, errorMessage);
			return PageName.AUTHORIZATION;
		}
		try {
			if (UserService.getInstance().matchExistLogin(registrLogin)) {
				errorMessage = CreateErrorMessage.createErrorMessage(LOGIN_EXISTS, userLocale);
				request.setAttribute(AttributeName.INVALID_REGISTR_DATA, errorMessage);
				return PageName.AUTHORIZATION;
			}
		} catch (ServiceException e) {
			System.out.println("ServiceException");
			throw new CommandException(e);
		}
		int userId = 0;
		try {
			userId = UserService.getInstance().checkRegister(registrLogin, registrPass, registrName, registrEmail);
		} catch (ServiceException e) {
			System.out.println("ServiceException2");
			throw new CommandException(e);
		}
		session.setAttribute(AttributeName.USER_ID, userId);
		session.setAttribute(AttributeName.USER_NAME, registrName);
		session.setAttribute(AttributeName.USER_ROLE, UserRole.USER.toString().toLowerCase());
		session.setAttribute(AttributeName.LAST_PAGE, PageName.USER_PAGE);
		return PageName.USER_PAGE;
	}


	private String validateData(String registrName, String registrLogin, String registrPass, String repeatedPass,
			String registrEmail, String userLocale) {
		Validation validation = Validation.getInstance();
		if (!validation.validateName(registrName)) {
			return CreateErrorMessage.createErrorMessage(INCORRECT_NAME, userLocale);
		}
		if (!validation.validateLogin(registrLogin)) {
			return CreateErrorMessage.createErrorMessage(INCORRECT_LOGIN, userLocale);
		}
		if (!validation.validatePassword(registrPass)) {
			return CreateErrorMessage.createErrorMessage(INCORRECT_PASSWORD, userLocale);
		}
		if (!validation.validatePassEqals(registrPass, repeatedPass)) {
			return CreateErrorMessage.createErrorMessage(PASSWORDS_DONT_MATCH, userLocale);
		}
		if (!validation.validateEmail(registrEmail)) {
			return CreateErrorMessage.createErrorMessage(INCORRECT_IMAIL, userLocale);
		}else {
			return null;
		}
	}
}