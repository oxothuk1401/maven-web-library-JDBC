package by.htp.library.command.impl;

import by.htp.library.command.AttributeName;
import by.htp.library.command.ICommand;
import by.htp.library.command.exception.CommandException;
import by.htp.library.entity.User;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class UserOperationCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(request.getParameter(AttributeName.USER_ID));
		String operation = request.getParameter(AttributeName.OPERATION);
		ArrayList<User> userList = (ArrayList<User>) session.getAttribute(AttributeName.USERS_LIST);
		try {
			switch (operation) {
				case AttributeName.UNBAN: UserService.getInstance().unBanUser(userId, userList);  break;
				case AttributeName.BAN: UserService.getInstance().banUser(userId, userList);  break;
				case AttributeName.DELETE: UserService.getInstance().delete(userId, userList); break;
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		session.setAttribute(AttributeName.USERS_LIST, userList);
		String page = (String) session.getAttribute(AttributeName.LAST_PAGE);
		return page;
	}
}
