package by.htp.library.dao;


import by.htp.library.dao.exception.DAOException;
import by.htp.library.entity.User;

import java.util.List;

public interface IUserDAO {

    User authorizeUser(String login, String password) throws DAOException;

    boolean checkRegister(String name, String login, String password, String email) throws DAOException;

    boolean checkMatchExistLogin(String login) throws DAOException;

    List<User> findAllUsers() throws DAOException;

    void banUser(int userId) throws DAOException;

    void unBanUser(int userId) throws DAOException;

    void delete(int userId) throws DAOException;

}
