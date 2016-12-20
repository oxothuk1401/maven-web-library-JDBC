package by.htp.library.command.impl;

import by.htp.library.command.AttributeName;
import by.htp.library.command.ICommand;
import by.htp.library.command.PageName;
import by.htp.library.controller.exception.CommandException;
import by.htp.library.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getSession().setAttribute(AttributeName.LAST_PAGE, PageName.AUTHORIZATION);
		return PageName.AUTHORIZATION;
	}
}
