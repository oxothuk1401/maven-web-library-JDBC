package by.htp.library.tag;

import by.htp.library.command.AttributeName;
import by.htp.library.entity.Book;
import by.htp.library.util.CreateErrorMessage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ShowAllBooks extends TagSupport {
    private static final long serialVersionUID = -450464079239593285L;
    private final static String LOCALE_PROPERTIES = "localization.locale";
    private static Logger logger = LogManager.getLogger(ShowAllBooks.class);
    private ArrayList<Book> bookList;
    private ResourceBundle bundle;

    public void setBookList(ArrayList<Book> list) {
        this.bookList = list;
    }

    @Override
    public int doStartTag() throws JspException {
        String userLocale = (String) pageContext.getSession().getAttribute(AttributeName.LOCALE);
        Locale locale;
        try {
            locale = (userLocale != null) ? new Locale(userLocale) : Locale.getDefault();
            bundle = ResourceBundle.getBundle(LOCALE_PROPERTIES, locale);
        } catch (MissingResourceException e) {
            logger.error(e);
            throw new RuntimeException("No access to the localization file");
        }
        String noBooks = bundle.getString("locale.messagge.no.books");
        String author = bundle.getString("local.author");
        String title = bundle.getString("local.title");
        String date = bundle.getString("local.date");
        String access = bundle.getString("local.access");
        String openAccess = bundle.getString("locale.message.openAccess");
        String closeAccess = bundle.getString("locale.message.closeAccess");
        String close = bundle.getString("locale.message.close");
        String open = bundle.getString("locale.message.open");
        String action = bundle.getString("locale.message.action");
        Object pageUnique = (Object) pageContext.getSession().getAttribute(AttributeName.PAGE_UNIQUE);
        try {
            JspWriter out = pageContext.getOut();
            if (bookList == null || bookList.isEmpty()) {
                out.write(noBooks);
            } else {
                out.write("<table align='center' class='table table-bordered table-condensed' style='width: 80%'>"
                        + "</th><th>" + author
                        + "</th><th>" + title
                        + "</th><th>" + date
                        + "</th><th>" + access
                        + "</th><th>" + action
                        + "</th></tr>");
                for (Book book : bookList) {
                    out.write("<tr><td>");
                    out.write(book.getAuthor());
                    out.write("</td><td>");
                    out.write(book.getTitle());
                    out.write("</td><td>");
                    out.write(book.getDate());
                    out.write("</td><td>");
                    if (book.getAccess().equals("notAvailable")) {
                        out.write("<p style='color: red'>" + close + "</p>");
                        out.write("</td><td>");
                        out.write(
                                "<form action='Controller' method='post'>" +
                                        "<input type='hidden' name='command' value='book-operation' />" +
                                        "<input type='hidden' name='operation' value='open' />" +
                                        "<input type='hidden' name='pageUnique' value='" + pageUnique + "' />" +
                                        "<input type='hidden' name='bookId' value='" + book.getBookId() + "'> " +
                                        "<input class='btn btn-info' type='submit' value='" + openAccess + "' />" +
                                        "</form>");

                    } else {
                        out.write("<p style='color: green'>" + open + "</p>");
                        out.write("</td><td>");
                        out.write(
                                "<form action='Controller' method='post'>" +
                                        "<input type='hidden' name='command' value='book-operation' />" +
                                        "<input type='hidden' name='operation' value='close' />" +
                                        "<input type='hidden' name='pageUnique' value='" + pageUnique + "' />" +
                                        "<input type='hidden' name='bookId' value='" + book.getBookId() + "'> " +
                                        "<input class='btn btn-primary' type='submit' value='" + closeAccess + "' />" +
                                        "</form>");
                    }

                    out.write("</td></tr>");
                }
                out.write("</table>");
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
