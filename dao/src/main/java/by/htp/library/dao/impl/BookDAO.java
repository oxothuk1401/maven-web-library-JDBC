package by.htp.library.dao.impl;


import by.htp.library.dao.IBookDAO;
import by.htp.library.dao.pool.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.entity.Book;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oxothuk1401 on 16.12.2016.
 */
public class BookDAO implements IBookDAO {
    private final static BookDAO INSTANCE = new BookDAO();
    private final static String AVALIBLE = "available";
    private static Logger logger = LogManager.getLogger(BookDAO.class);
    private final static String FIND_ALL_BOOKS = "SELECT * FROM book";
    private final static String CHECK_SEARCH = "SELECT * FROM book ORDER BY ";
    private final static String ADD_BOOK = "insert into book(access, author, title, date) values(?,?,?,?)";
    private final static String OPEN_ACCESS = "update book set access = 'available' where idbook = ?";
    private final static String CLOSE_ACCESS = "update book set access = 'notAvailable' where idbook = ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private BookDAO() {
    }

    public static BookDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean addBook(String addAuthor, String addTitle, String addDate) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
                preparedStatement = connection.prepareStatement(ADD_BOOK);
                preparedStatement.setString(1, AVALIBLE);
                preparedStatement.setString(2, addAuthor);
                preparedStatement.setString(3, addTitle);
                preparedStatement.setString(4, addDate);
                preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Add book fault", e);
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


    public List<Book> checkSearch(String searching, String sorted) throws DAOException {
        List<Book> listBooks = new ArrayList<>();
        String str;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(CHECK_SEARCH + sorted);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(2).equals(AVALIBLE)) {
                    str = resultSet.getString(3) + " " +
                            resultSet.getString(4) + " " +
                            resultSet.getString(5);
                    if (str.toLowerCase().contains(searching.toLowerCase())) {
                        listBooks.add(new Book(resultSet.getLong(1),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5)));
                    }
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Search fault", e);
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
        return listBooks;
    }

    @Override
    public ArrayList<Book> findAllBooks() throws DAOException {
        ArrayList<Book> bookList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_BOOKS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getLong(1));
                book.setAccess(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setTitle(resultSet.getString(4));
                book.setDate(resultSet.getString(5));
                bookList.add(book);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Find all books fault", e);
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
        return bookList;
    }

    @Override
    public void openAccess(int bookId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(OPEN_ACCESS);
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Open access fault", e);
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
    public void closeAccess(int bookId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(CLOSE_ACCESS);
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Close access fault", e);
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
