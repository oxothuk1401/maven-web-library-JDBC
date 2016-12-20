package by.htp.library.service;


import by.htp.library.entity.User;
import by.htp.library.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public interface IUserService {

	User authorizeUser(String login, String password) throws ServiceException;

	int checkRegister(String login, String password, String name, String email) throws ServiceException;

	boolean matchExistLogin(String login) throws ServiceException;

	List<User> findAllUsers() throws ServiceException;

	void banUser(int userId, ArrayList<User> userList) throws ServiceException;

	void unBanUser(int userId, ArrayList<User> userList) throws ServiceException;

	void delete(int userId, ArrayList<User> userList) throws ServiceException;
}
