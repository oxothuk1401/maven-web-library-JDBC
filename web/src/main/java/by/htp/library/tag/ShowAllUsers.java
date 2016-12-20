package by.htp.library.tag;

import by.htp.library.command.AttributeName;
import by.htp.library.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ShowAllUsers extends TagSupport {
    private static final long serialVersionUID = -450464079239593285L;
    private final static String LOCALE_PROPERTIES = "localization.locale";
    private ArrayList<User> userList;
    private ResourceBundle bundle;

    public void setUserList(ArrayList<User> list) {
        this.userList = list;
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
        String noUsers = bundle.getString("locale.messagge.no.users");
        String userName = bundle.getString("locale.message.client.name");
        String userLogin = bundle.getString("locale.message.user.username");
        String eMail = bundle.getString("locale.message.email");
        String banned = bundle.getString("locale.message.banned");
        String notBanned = bundle.getString("locale.message.not.banned");
        String ban = bundle.getString("locale.button.ban");
        String delete = bundle.getString("local.Deleteuser");
        String unban = bundle.getString("locale.button.unban");
        String status = bundle.getString("locale.message.status");
        String action = bundle.getString("locale.message.action");
        Object pageUnique = (Object) pageContext.getSession().getAttribute(AttributeName.PAGE_UNIQUE);
        try {
            JspWriter out = pageContext.getOut();
            if (userList == null || userList.isEmpty()) {
                out.write(noUsers);
            } else {
                out.write("<table align='center' class='table table-bordered table-condensed' style='width: 60%'>"
                        + "<tr><th>" + userName
                        + "</th><th>" + userLogin
                        + "</th><th>" + eMail
                        + "</th><th>" + status
                        + "</th><th>" + action
                        + "</th></tr>");
                for (User user : userList) {
                    out.write("<tr><td>");
                    out.write(user.getName());
                    out.write("</td><td>");
                    out.write(user.getLogin());
                    out.write("</td><td>");
                    out.write(user.geteMail());
                    out.write("</td><td>");
                    if (user.getBlacklist().equals("block")) {
                        out.write("<p style='color: red'>" + banned + "</p>");
                        out.write("</td><td>");
                        out.write(
                                "<form action='Controller' method='post'>" +
                                        "<input type='hidden' name='command' value='user-operation' />" +
                                        "<input type='hidden' name='operation' value='unban' />" +
                                        "<input type='hidden' name='pageUnique' value='" + pageUnique + "' />" +
                                        "<input type='hidden' name='userId' value='" + user.getUserId() + "'> " +
                                        "<input class='btn btn-info' type='submit' value='" + unban + "' />" +
                                        "</form>");

                    } else {
                        out.write("<p style='color: green'>" + notBanned + "</p>");
                        out.write("</td><td>");
                        out.write(
                                "<form action='Controller' method='post'>" +
                                        "<input type='hidden' name='command' value='user-operation' />" +
                                        "<input type='hidden' name='operation' value='ban' />" +
                                        "<input type='hidden' name='pageUnique' value='" + pageUnique + "' />" +
                                        "<input type='hidden' name='userId' value='" + user.getUserId() + "'> " +
                                        "<input class='btn btn-primary' type='submit' value='" + ban + "' />" +
                                        "</form>");
                    }
                    out.write("</td><td>");
                    out.write(
                            "<form action='Controller' method='post'>" +
                                    "<input type='hidden' name='command' value='user-operation' />" +
                                    "<input type='hidden' name='operation' value='delete' />" +
                                    "<input type='hidden' name='pageUnique' value='" + pageUnique + "' />" +
                                    "<input type='hidden' name='userId' value='" + user.getUserId() + "'> " +
                                    "<input class='btn btn-warning' type='submit' value='" + delete + "' />" +
                                    "</form>");
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
