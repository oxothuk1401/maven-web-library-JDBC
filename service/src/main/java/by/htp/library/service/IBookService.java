package by.htp.library.service;

import by.htp.library.entity.Book;
import by.htp.library.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by oxothuk1401 on 16.12.2016.
 */
public interface IBookService {


    boolean addBook(String addAuthor, String addTitle, String addDate) throws ServiceException;

    List<by.htp.library.entity.Book> checkSearch(String searching, String sorted) throws ServiceException;

    ArrayList<by.htp.library.entity.Book> findAllBooks() throws ServiceException;

    void openAccess(int bookId, ArrayList<Book> bookList) throws ServiceException;

    void closeAccess(int bookId, ArrayList<Book> bookList) throws ServiceException;
}
