package by.htp.library.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.library.command.AttributeName;
import by.htp.library.command.CommandHelper;
import by.htp.library.command.ICommand;
import by.htp.library.command.PageName;
import by.htp.library.command.exception.CommandException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class Controller
 *
 * @author Sergei Levkovskii
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static Logger logger = LogManager.getLogger(Controller.class);
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    /**
     * Uses pattern command, to make some actions on the server and return
     * correct jsp page. Or return error page, if error has occurred.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String commandName = request.getParameter(AttributeName.COMMAND);
        String pageName;
        try {
            ICommand command = CommandHelper.getCommand(commandName);
            pageName = command.execute(request, response);
        } catch (CommandException e) {
            session.setAttribute(AttributeName.ERROR_MESSAGE, e.getMessage());
            logger.error(e);
            pageName = PageName.ERROR_PAGE;
        }
            request.getRequestDispatcher(pageName).forward(request, response);
    }
}