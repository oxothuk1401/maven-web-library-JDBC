package by.htp.library.command.impl;

import by.htp.library.command.AttributeName;
import by.htp.library.command.ICommand;
import by.htp.library.command.PageName;
import by.htp.library.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LocalizationCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		HttpSession session = request.getSession();
		String locale = request.getParameter(AttributeName.LOCALE);
		session.setAttribute(AttributeName.LOCALE, locale);
		String lastPage = (String)session.getAttribute(AttributeName.LAST_PAGE);
		if (lastPage == null) {
			return PageName.AUTHORIZATION;
		} else {
			return lastPage;
		}
	}
}
