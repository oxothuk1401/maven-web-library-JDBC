package by.htp.library.service;


import by.htp.library.entity.User;
import by.htp.library.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
/**
 * Makes some service actions.
 * calls correspondig DAO
 *
 * @author Sergei Levkovskii
 *
 */
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

    /**
     * Add a new user to the database.
     *
     * @param name     user name
     * @param login    user login
     * @param password user password
     * @param email    user Email
     * @return true if user add to the database, and false if adding happened
     * @throws ServiceException
     */
    boolean checkRegister(String name, String login, String password, String email) throws ServiceException;

    /**
     * Check login for the unique. Compare login to the database logins.
     *
     * @param login login which must be checked
     * @return true if login is free, or false if login not free
     * @throws ServiceException
     */
    boolean matchExistLogin(String login) throws ServiceException;

    /**
     * Find all users in databese.
     *
     * @return list of users.
     * @throws ServiceException
     */
    List<User> findAllUsers() throws ServiceException;

    /**
     * Ban user. Banned user cannot make some actions.
     *
     * @param userId ID of a user who must be banned
     * @throws ServiceException
     */
    void banUser(int userId, ArrayList<User> userList) throws ServiceException;

    /**
     * Unlock user.
     *
     * @param userId ID of a user who must be unbanned
     * @throws ServiceException
     */
    void unBanUser(int userId, ArrayList<User> userList) throws ServiceException;

    /**
     * Delete user.
     *
     * @param userId ID of a user who must be deleted
     * @throws ServiceException
     */
    void delete(int userId, ArrayList<User> userList) throws ServiceException;
}
