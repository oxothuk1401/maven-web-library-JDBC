package by.htp.library.dao.impl;


import by.htp.library.dao.IUserDAO;
import by.htp.library.dao.util.PasswordEncryption;
import by.htp.library.dao.pool.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.entity.User;
import by.htp.library.entity.UserRole;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO implements IUserDAO {
    private final static UserDAO INSTANCE = new UserDAO();
    private final static String USER = "user";
    private final static String UNBLOCK = "unblock";
    private static Logger logger = LogManager.getLogger(UserDAO.class);
    private final static String CHECK_LOGIN = "SELECT * FROM User";
    private final static String CHECK_MATCH_LOGIN = "SELECT login FROM user WHERE login = ?";
    private final static String CHECK_REGISTER = "insert into user(login, password, role, blacklist, name, email) values(?,?,?,?,?,?)";
    private final static String UNBAN_USER = "update user set blacklist = 'unblock' where iduser = ?";
    private final static String BAN_USER = "update user set blacklist = 'block' where iduser = ?";
    private final static String DELETE_USER = "DELETE FROM user WHERE iduser = ?";
    private final static String FIND_ALL_USERS = "SELECT * FROM User";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public User authorizeUser(String login, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        User user = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(CHECK_LOGIN);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (login.equals(resultSet.getString(2)) && PasswordEncryption.takeMD5Function(password).equals(resultSet.getString(3))) {
                    user = new User();
                    user.setUserId(resultSet.getInt(1));
                    user.setLogin(resultSet.getString(2));
                    user.setPassword(resultSet.getString(3));
                    user.setRole(UserRole.valueOf(resultSet.getString(4).toUpperCase()));
                    user.setBlacklist(resultSet.getString(5));
                    user.setName(resultSet.getString(6));
                    break;
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error accessing database", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Error close preparedStatement");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error close connection");
                }
            }
        }
        return user;
    }

    @Override
    public boolean checkMatchExistLogin(String login) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(CHECK_MATCH_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.equals(login)) {
                    break;
                } else {
                    return true;
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error accessing database", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Error close preparedStatement");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error close connection");
                }
            }
        }
        return false;
    }

    @Override
    public boolean checkRegister(String name, String login, String password, String email) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
                preparedStatement = connection.prepareStatement(CHECK_REGISTER);
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, PasswordEncryption.takeMD5Function(password));
                preparedStatement.setString(3, USER);
                preparedStatement.setString(4, UNBLOCK);
                preparedStatement.setString(5, name);
                preparedStatement.setString(6, email);
                preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error accessing database", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Error close preparedStatement");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error close connection");
                }
            }
        }
        return true;
    }

    @Override
    public ArrayList<User> findAllUsers() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        ArrayList<User> userList = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRole(UserRole.valueOf(resultSet.getString(4).toUpperCase()));
                user.setBlacklist(resultSet.getString(5));
                user.setName(resultSet.getString(6));
                user.seteMail(resultSet.getString(7));
                userList.add(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error accessing database", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Error close preparedStatement");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error close connection");
                }
            }
        }
        return userList;
    }

    @Override
    public void banUser(int userId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(BAN_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error accessing database", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Error close preparedStatement");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error close connection");
                }
            }
        }
    }

    @Override
    public void unBanUser(int userId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(UNBAN_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error accessing database", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Error close preparedStatement");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error close connection");
                }
            }
        }
    }

    @Override
    public void delete(int userId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error accessing database", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Error close preparedStatement");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error close connection");
                }
            }
        }
    }
}


