package by.htp.library.service;

import by.htp.library.entity.Book;
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
public interface IBookService {
    /**
     * Add a new book to the database.
     *
     * @param addAuthor book author
     * @param addTitle  book title
     * @param addDate   book date of publication
     * @return true if book add to the database, and false if adding happened
     * @throws ServiceException
     */
    boolean addBook(String addAuthor, String addTitle, String addDate) throws ServiceException;
    /**
     * Book search and sorting in the database.
     *
     * @param searching search criterion
     * @param sorted  sort by
     * @return list of books.
     * @throws ServiceException
     */
    List<Book> checkSearch(String searching, String sorted) throws ServiceException;
    /**
     * Find all books in database.
     *
     * @throws ServiceException
     */
    ArrayList<Book> findAllBooks() throws ServiceException;
    /**
     * Open access.
     *
     * @param bookId ID of a book who must be opened access.
     * @throws ServiceException
     */
    void openAccess(int bookId, ArrayList<Book> bookList) throws ServiceException;
    /**
     * Close access for users.
     *
     * @param bookId ID of a book who must be closed access.
     * @throws ServiceException
     */
    void closeAccess(int bookId, ArrayList<Book> bookList) throws ServiceException;
}
