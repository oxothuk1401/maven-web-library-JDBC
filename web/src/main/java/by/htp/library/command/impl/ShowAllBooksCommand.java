package by.htp.library.command.impl;


import by.htp.library.command.AttributeName;
import by.htp.library.command.ICommand;
import by.htp.library.command.PageName;
import by.htp.library.command.exception.CommandException;
import by.htp.library.entity.Book;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.impl.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ShowAllBooksCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        ArrayList<Book> bookList;
        try {
            bookList = BookService.getInstance().findAllBooks();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        session.setAttribute(AttributeName.BOOKS_LIST, bookList);
        session.setAttribute(AttributeName.LAST_PAGE, PageName.SHOW_ALL_BOOKS);
        return PageName.SHOW_ALL_BOOKS;
    }
}
