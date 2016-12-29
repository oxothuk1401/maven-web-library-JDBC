package by.htp.library.command.impl;


import by.htp.library.command.AttributeName;
import by.htp.library.command.ICommand;
import by.htp.library.command.PageName;
import by.htp.library.command.exception.CommandException;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.impl.BookService;
import by.htp.library.util.CreateErrorMessage;
import by.htp.library.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddBookCommand implements ICommand {
    private final static String CORRECT_DATA = "locale.error.message.correct";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        String userLocale = (String) session.getAttribute(AttributeName.LOCALE);
        String addAuthor = request.getParameter(AttributeName.ADD_AUTHOR);
        String addTitle = request.getParameter(AttributeName.ADD_TITLE);
        String addDate = request.getParameter(AttributeName.ADD_DATE);
        String errorMessage = CreateErrorMessage.createErrorMessage(CORRECT_DATA, userLocale);
        if (!validateData(addAuthor, addTitle, addDate)) {
            request.setAttribute(AttributeName.WRONG_DATA, errorMessage);
            return PageName.USER_PAGE;
        }
        try {
            BookService.getInstance().addBook(addAuthor, addTitle, addDate);
            request.setAttribute(AttributeName.SUCCESS_OPERATION, true);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        session.setAttribute(AttributeName.LAST_PAGE, PageName.USER_PAGE);
        return PageName.USER_PAGE;
    }

    private boolean validateData(String addAuthor, String addTitle, String addDate) {
        Validation validation = Validation.getInstance();
        return validation.validateAuthor(addAuthor) && validation.validateTitle(addTitle) && validation.validateDate(addDate);
    }


}