package by.htp.library.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.command.*;
import by.htp.library.command.exception.CommandException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static Logger logger = LogManager.getLogger(Controller.class);
    private static final long serialVersionUID = 1L;


    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter(AttributeName.COMMAND);
        String pageName;
        try {
            ICommand command = CommandHelper.getCommand(commandName);
            pageName = command.execute(request, response);
        } catch (CommandException e) {
            logger.error(e);
            pageName = PageName.ERROR_PAGE;
        }
        if (pageName != PageName.AJAX) {
            request.getRequestDispatcher(pageName).forward(request, response);
        } else {
            request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
        }
    }
}
