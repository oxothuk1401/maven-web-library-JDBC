package by.htp.library.service;


import by.htp.library.entity.User;
import by.htp.library.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public interface IUserService {
    /**
     * Authorization of the user in the system.
     *
     * @param login
     * @param password
     * @return if login and password are correct, return object User whith all
     * necessary data.
     * @throws ServiceException
     */
    User authorizeUser(String login, String password) throws ServiceException;

    boolean checkRegister(String name, String login, String password, String email) throws ServiceException;

    boolean matchExistLogin(String login) throws ServiceException;

    List<User> findAllUsers() throws ServiceException;

    void banUser(int userId, ArrayList<User> userList) throws ServiceException;

    void unBanUser(int userId, ArrayList<User> userList) throws ServiceException;

    void delete(int userId, ArrayList<User> userList) throws ServiceException;
}
