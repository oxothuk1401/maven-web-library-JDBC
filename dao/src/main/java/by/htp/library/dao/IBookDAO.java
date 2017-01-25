package by.htp.library.dao;


import by.htp.library.dao.exception.DAOException;
import by.htp.library.entity.Book;

import java.util.List;

/**
 * Connects to the database, and make some actions.
 *
 * @author Sergei Levkovskii
 */
public interface IBookDAO {
    /**
     * Add a new book to the database.
     *
     * @param addAuthor book author
     * @param addTitle  book title
     * @param addDate   book date of publication
     * @return true if book add to the database, and false if adding happened
     * @throws DAOException
     */
    boolean addBook(String addAuthor, String addTitle, String addDate) throws DAOException;

    /**
     * Book search and sorting in the database.
     *
     * @param searching search criterion
     * @param sorted    sort by
     * @return list of books.
     * @throws DAOException
     */
    List<Book> checkSearch(String searching, String sorted) throws DAOException;

    /**
     * Find all books in database.
     *
     * @throws DAOException
     */
    List<Book> findAllBooks() throws DAOException;

    /**
     * Open access.
     *
     * @param bookId ID of a book who must be opened access.
     * @throws DAOException
     */
    void openAccess(int bookId) throws DAOException;

    /**
     * Close access for users.
     *
     * @param bookId ID of a book who must be closed access.
     * @throws DAOException
     */
    void closeAccess(int bookId) throws DAOException;
}


