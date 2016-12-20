package by.htp.library.command.impl;


import by.htp.library.command.AttributeName;
import by.htp.library.command.ICommand;
import by.htp.library.command.PageName;
import by.htp.library.controller.exception.CommandException;
import by.htp.library.entity.User;
import by.htp.library.entity.UserRole;
import by.htp.library.service.BookService;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.utils.CreateErrorMessage;
import by.htp.library.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddBookCommand implements ICommand {
    private final static String CORRECT_DATA = "locale.error.message.correct";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        String userLocale = (String) session.getAttribute(AttributeName.LOCALE);
        String addAuthor = request.getParameter(AttributeName.ADD_AUTHOR);
        String addTitle = request.getParameter(AttributeName.ADD_TITLE);
        String addDate = request.getParameter(AttributeName.ADD_DATE);
        String addAmount = request.getParameter(AttributeName.ADD_AMOUNT);
        String errorMessage = CreateErrorMessage.createErrorMessage(CORRECT_DATA,  userLocale);
        try {
            if(BookService.getInstance().addBook(addAuthor,addTitle,addDate,addAmount)){
                request.setAttribute(AttributeName.SUCCESS_OPERATION, true);
            }else{
                request.setAttribute(AttributeName.WRONG_DATA, errorMessage);
            }
        } catch (ServiceException e) {
            session.setAttribute(AttributeName.ERROR_MESSAGE, e.getMessage());
            throw new CommandException(e);
        }
        session.setAttribute(AttributeName.LAST_PAGE, PageName.USER_PAGE);
        return PageName.USER_PAGE;
    }




}