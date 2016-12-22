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
import java.util.List;

public class SearchBookCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  throws CommandException {
        HttpSession session = request.getSession();
        String searching = request.getParameter(AttributeName.SEARCHING);
        String sorted = request.getParameter(AttributeName.SORTED);
        List<Book> bookList = null;
        try {
            bookList = BookService.getInstance().checkSearch(searching,sorted);
            session.setAttribute(AttributeName.BOOKS_LIST, bookList);
        } catch (ServiceException e) {
          throw new CommandException(e.getMessage());
        }
        session.setAttribute(AttributeName.LAST_PAGE, PageName.SHOW_SEARCH_BOOK);
    return PageName.SHOW_SEARCH_BOOK;
    }

}
