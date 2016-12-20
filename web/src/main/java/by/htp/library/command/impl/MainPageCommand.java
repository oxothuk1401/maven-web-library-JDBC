package by.htp.library.command.impl;


import by.htp.library.command.AttributeName;
import by.htp.library.command.ICommand;
import by.htp.library.command.PageName;
import by.htp.library.controller.exception.CommandException;
import by.htp.library.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainPageCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		HttpSession session = request.getSession();
		if (session.getAttribute(AttributeName.USER_ID) == null) {
			session.setAttribute(AttributeName.LAST_PAGE, PageName.AUTHORIZATION);
			return PageName.AUTHORIZATION;
		} else {
			session.setAttribute(AttributeName.LAST_PAGE, PageName.USER_PAGE);
			return PageName.USER_PAGE;
		}
	}
}
