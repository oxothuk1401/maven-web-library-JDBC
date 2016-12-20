package by.htp.library.service;

import by.htp.library.dao.BookDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.entity.Book;
import by.htp.library.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oxothuk1401 on 16.12.2016.
 */
public class BookService implements IBookService {
    private final static BookService INSTANCE = new BookService();

    private BookService() {
    }

    public static BookService getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean addBook(String addAuthor, String addTitle, String addDate, String addAmount) throws ServiceException {
        BookDAO bookDAO = BookDAO.getInstance();
        boolean result = false;
        try {
            result = bookDAO.addBook(addAuthor,addTitle,addDate,addAmount);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Book> checkSearch(String searching, String sorted) throws ServiceException {
        BookDAO bookDAO = BookDAO.getInstance();
        List<Book> bookList = null;

        try {
            bookList = bookDAO.checkSearch(searching,sorted);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

        return bookList;
    }

    @Override
    public ArrayList<Book> findAllBooks() throws ServiceException {
        ArrayList<Book> bookList;
        try {
            BookDAO bookDAO = BookDAO.getInstance();
            bookList = bookDAO.findAllBooks();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return bookList;
    }
}
