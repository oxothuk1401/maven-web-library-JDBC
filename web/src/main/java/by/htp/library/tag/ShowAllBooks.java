package by.htp.library.tag;

import by.htp.library.command.AttributeName;
import by.htp.library.entity.Book;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class ShowAllBooks extends TagSupport {
    private static final long serialVersionUID = -450464079239593285L;
    private final static String LOCALE_PROPERTIES = "localization.locale";
    private ArrayList<Book> bookList;
    private ResourceBundle bundle;

    public void setBookList(ArrayList<Book> list) {
        this.bookList = list;
    }

    @Override
    public int doStartTag() throws JspException {
        String userLocale = (String) pageContext.getSession().getAttribute(AttributeName.LOCALE);
        Locale locale;
        if (userLocale == null) {
            locale = Locale.getDefault();
        } else {
            locale = new Locale(userLocale);
        }
        bundle = ResourceBundle.getBundle(LOCALE_PROPERTIES, locale);
        String noBooks = bundle.getString("locale.messagge.no.books");
        String id = bundle.getString("local.id");
        String author = bundle.getString("local.author");
        String title = bundle.getString("local.title");
        String amount = bundle.getString("local.amount");
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
                        + "<tr><th>" + id
                        + "</th><th>" + author
                        + "</th><th>" + title
                        + "</th><th>" + amount
                        + "</th><th>" + date
                        + "</th><th>" + access
                        + "</th><th>" + action
                        + "</th></tr>");
                for (Book book : bookList) {
                    out.write("<tr><td>");
                    out.write(Integer.toString(book.getIdbooks()));
                    out.write("</td><td>");
                    out.write(book.getAuthor());
                    out.write("</td><td>");
                    out.write(book.getTitle());
                    out.write("</td><td>");
                    out.write(Integer.toString(book.getAmount()));
                    out.write("</td><td>");
                    out.write(book.getDate());
                    out.write("</td><td>");
                    if (book.getAccess().equals("NotAvailable")) {
                        out.write("<p style='color: red'>" + close + "</p>");
                        out.write("</td><td>");
                        out.write(
                                "<form action='Controller' method='post'>" +
                                        "<input type='hidden' name='command' value='book-operation' />" +
                                        "<input type='hidden' name='operation' value='open' />" +
                                        "<input type='hidden' name='pageUnique' value='" + pageUnique + "' />" +
                                        "<input type='hidden' name='userId' value='" + book.getIdbooks() + "'> " +
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
                                        "<input type='hidden' name='userId' value='" + book.getIdbooks() + "'> " +
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
