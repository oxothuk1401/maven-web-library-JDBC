package by.htp.library.dao;

import by.htp.library.dao.connectionpool.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.entity.Book;

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
    private final static String FIND_ALL_BOOKS = "SELECT * FROM books";
    private final static String CHECK_SEARCH = "SELECT * FROM books ORDER BY ";
    private final static String ADD_BOOK = "insert into books(access, author, title, date, location, amount) values(?,?,?,?,?,?)";
    private final static String OPEN_ACCESS = "update books set access = 'available' where idbooks = ?";
    private final static String CLOSE_ACCESS = "update books set access = 'notAvailable' where idbooks = ?";

    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    private BookDAO() {
    }

    public static BookDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean addBook(String addAuthor, String addTitle, String addDate, String addAmount) throws DAOException {
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(ADD_BOOK);
            preparedStatement.setString(1, "available");
            preparedStatement.setString(2, addAuthor);
            preparedStatement.setString(3, addTitle);
            preparedStatement.setString(4, addDate);
            preparedStatement.setString(5, "library");
            preparedStatement.setString(6, addAmount);
            preparedStatement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            return false;
        }
    }


    public List<Book> checkSearch(String searching, String sorted) throws DAOException {
        List<Book> listBooks = new ArrayList<>();
        String str = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(CHECK_SEARCH + sorted);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                str = resultSet.getString(3) + " " +
                        resultSet.getString(4) + " " +
                        resultSet.getString(5);
                if (str.toLowerCase().contains(searching.toLowerCase())) {
                    listBooks.add(new Book(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getInt(7)));
                }
            }
            connection.close();
            return listBooks;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Search fault");
        }


    }

    @Override
    public ArrayList<Book> findAllBooks() throws DAOException {
        ArrayList<Book> bookList = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_BOOKS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setIdbooks(resultSet.getInt(1));
                book.setAccess(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setTitle(resultSet.getString(4));
                book.setDate(resultSet.getString(5));
                book.setLocation(resultSet.getString(6));
                book.setAmount(resultSet.getInt(7));
                bookList.add(book);
            }
            connection.close();
            return bookList;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Find all books fault", e);
        }
    }

    @Override
    public void openAccess(int bookId) throws DAOException {
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(OPEN_ACCESS);
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Open access fault", e);
        }
    }

    @Override
    public void closeAccess(int bookId) throws DAOException {

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(CLOSE_ACCESS);
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Close access fault", e);
        }
    }

}
