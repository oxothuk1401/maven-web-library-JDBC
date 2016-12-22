package by.htp.library.service.impl;

import by.htp.library.dao.impl.UserDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.entity.User;
import by.htp.library.service.IUserService;
import by.htp.library.service.exception.ServiceException;

import java.util.ArrayList;


public class UserService implements IUserService {
	private final static UserService INSTANCE = new UserService();

	private UserService() {
	}

	public static UserService getInstance() {
		return INSTANCE;
	}

	public User authorizeUser(String login, String password) throws ServiceException {
		User user = null;
		try {
			UserDAO userDAO = UserDAO.getInstance();
			user = userDAO.authorizeUser(login,password);
			return user;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean matchExistLogin(String login) throws ServiceException {
		try {
			UserDAO userDAO = UserDAO.getInstance();
			return userDAO.checkMatchExistLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int checkRegister(String login, String password, String name, String email) throws ServiceException {
		try {
			UserDAO userDAO = UserDAO.getInstance();
			return userDAO.checkRegister(login, password, name, email);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public ArrayList<User> findAllUsers() throws ServiceException {
		ArrayList<User> userList;
		try {
			UserDAO userDAO = UserDAO.getInstance();
			userList = userDAO.findAllUsers();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return userList;
	}

	@Override
	public void banUser(int userId, ArrayList<User> userList) throws ServiceException {
		try {
			UserDAO userDAO = UserDAO.getInstance();
			userDAO.banUser(userId);
			userList.stream().filter(user -> user.getUserId() == userId).forEach(user -> user.setBlacklist("block"));
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void unBanUser(int userId, ArrayList<User> userList) throws ServiceException {
		try {
			UserDAO userDAO = UserDAO.getInstance();
			userDAO.unBanUser(userId);
			userList.stream().filter(user -> user.getUserId() == userId).forEach(user -> user.setBlacklist("unblock"));
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void delete(int userId, ArrayList<User> userList) throws ServiceException {
		try {
			UserDAO userDAO = UserDAO.getInstance();
			userDAO.delete(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}

