package by.htp.library.dao;

import by.htp.library.dao.connectionpool.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.entity.User;
import by.htp.library.entity.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO implements IUserDAO {
    private final static UserDAO INSTANCE = new UserDAO();
    private final static String CHECK_LOGIN = "SELECT * FROM Users";
    private final static String CHECK_MATCH_LOGIN = "SELECT login FROM users WHERE login = ?";
    private final static String CHECK_REGISTER = "insert into users(login, password, Role, blacklist, name, email) values(?,?,?,?,?,?)";
    private final static String FIND_ID_BY_LOGIN = "SELECT idusers FROM users WHERE login = ?";
    private final static String DELETE_USER = "DELETE FROM users WHERE login=";
    private final static String GET_USERS = "SELECT login FROM Users";
    private final static String GET_BOOKS = "SELECT access, author, title, date, location FROM books";
    private final static String CHECK_SEARCH = "SELECT * FROM books";
    private final static String UNBAN_USER = "update users set blacklist = 'unblock' where idusers = ?";
    private final static String BAN_USER = "update users set blacklist = 'block' where idusers = ?";
    private final static String DELETE = "DELETE FROM users where idusers = ?";
    private final static String FIND_ALL_USERS = "SELECT * FROM Users";
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public User authorizeUser(String login, String password) throws DAOException {
        User user = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(CHECK_LOGIN);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (login.equals(resultSet.getString(2)) && MD5.getMD5(password).equals(resultSet.getString(3))) {
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
            connection.close();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error accessing database");
        }
        return user;
    }

    @Override
    public boolean checkMatchExistLogin(String login) throws DAOException {
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
            connection.close();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error accessing database");
        }
        return false;
    }

    @Override
    public int checkRegister(String login, String password, String name, String email) throws DAOException {
        int userId = 0;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(CHECK_REGISTER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, MD5.getMD5(password));
            preparedStatement.setString(3, "user");
            preparedStatement.setString(4, "unblock");
            preparedStatement.setString(5, name);
            preparedStatement.setString(6, email);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error accessing database");
        }
        try {
            preparedStatement = connection.prepareStatement(FIND_ID_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt(1);
            }
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("Error accessing database");
        }
        return userId;
    }

    public ArrayList<User> findAllUsers() throws DAOException {
        ArrayList<User> usersList = new ArrayList<>();
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
                usersList.add(user);
            }
            connection.close();
            return usersList;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Change driver car fault", e);
        }
    }

    @Override
    public void banUser(int userId) throws DAOException {
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(BAN_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Ban user failure", e);
        }
    }

    @Override
    public void unBanUser(int userId) throws DAOException {
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(UNBAN_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Unban user failure", e);
        }
    }

    @Override
    public void delete(int userId) throws DAOException {
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Delete user failure", e);
        }
    }
}


