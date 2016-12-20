package by.htp.library.command.impl;

;

import by.htp.library.command.AttributeName;
import by.htp.library.command.ICommand;
import by.htp.library.command.PageName;
import by.htp.library.controller.exception.CommandException;
import by.htp.library.entity.User;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ShowAllUsersCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		HttpSession session = request.getSession();
		ArrayList<User> userList;
		try {
			userList = UserService.getInstance().findAllUsers();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}

		session.setAttribute(AttributeName.USERS_LIST, userList);
		session.setAttribute(AttributeName.LAST_PAGE, PageName.SHOW_ALL_USERS);
		return PageName.SHOW_ALL_USERS;
	}
}
