package by.htp.library.dao;


import by.htp.library.dao.exception.DAOException;
import by.htp.library.entity.User;

import java.util.List;
/**
 * Connects to the database, and make some actions.
 *
 * @author Sergei Levkovskii
 *
 */
public interface IUserDAO {
    /**
     * Authorization of the user in the system.
     *
     * @param login
     * @param password
     * @return if login and password are correct, return object User whith all
     * necessary data.
     * @throws DAOException
     */
    User authorizeUser(String login, String password) throws DAOException;
    /**
     * Add a new user to the database.
     *
     * @param name     user name
     * @param login    user login
     * @param password user password
     * @param email    user Email
     * @return true if user add to the database, and false if adding happened
     * @throws DAOException
     */
    boolean checkRegister(String name, String login, String password, String email) throws DAOException;
    /**
     * Check login for the unique. Compare login to the database logins.
     *
     * @param login login which must be checked
     * @return true if login is free, or false if login not free
     * @throws DAOException
     */
    boolean checkMatchExistLogin(String login) throws DAOException;
    /**
     * Find all users in databese.
     *
     * @return list of users.
     * @throws DAOException
     */
    List<User> findAllUsers() throws DAOException;
    /**
     * Ban user. Banned user cannot make some actions.
     *
     * @param userId ID of a user who must be banned
     * @throws DAOException
     */
    void banUser(int userId) throws DAOException;
    /**
     * Unlock user.
     *
     * @param userId ID of a user who must be unbanned
     * @throws DAOException
     */
    void unBanUser(int userId) throws DAOException;
    /**
     * Delete user.
     *
     * @param userId ID of a user who must be deleted
     * @throws DAOException
     */
    void delete(int userId) throws DAOException;

}
