package by.htp.library.command.impl;

import by.htp.library.command.AttributeName;
import by.htp.library.command.ICommand;
import by.htp.library.controller.exception.CommandException;
import by.htp.library.entity.Book;
import by.htp.library.entity.User;
import by.htp.library.service.BookService;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class BookOperationCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        int bookId = Integer.parseInt(request.getParameter(AttributeName.BOOK_ID));
        String operation = request.getParameter(AttributeName.OPERATION);
        ArrayList<Book> bookList = (ArrayList<Book>) session.getAttribute(AttributeName.BOOKS_LIST);
        try {
            switch (operation) {
                case "open": BookService.getInstance().openAccess(bookId, bookList);  break;
                case "close": BookService.getInstance().closeAccess(bookId, bookList);  break;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        session.setAttribute(AttributeName.BOOKS_LIST, bookList);
        String page = (String) session.getAttribute(AttributeName.LAST_PAGE);
        return page;
    }
}
