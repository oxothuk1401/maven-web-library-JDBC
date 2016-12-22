package by.htp.library.dao;


import by.htp.library.dao.exception.DAOException;
import by.htp.library.entity.Book;

import java.util.List;

public interface IBookDAO {

    boolean addBook(String addAuthor, String addTitle, String addDate) throws DAOException;

    List<Book> checkSearch(String searching, String sorted) throws DAOException;

    List<Book> findAllBooks() throws DAOException;

    void openAccess(int bookId) throws DAOException;

    void closeAccess(int bookId) throws DAOException;
}


