package by.htp.library.service;

import by.htp.library.entity.Book;
import by.htp.library.service.exception.ServiceException;

import java.util.List;

/**
 * Created by oxothuk1401 on 16.12.2016.
 */
public interface IBookService {


    boolean addBook(String addAuthor, String addTitle, String addDate, String addAmount) throws ServiceException;

    List<Book> checkSearch(String searching, String sorted) throws ServiceException;

    List<Book> findAllBooks() throws ServiceException;
}
