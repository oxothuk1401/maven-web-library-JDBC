package by.htp.library.entity;

import java.io.Serializable;
import java.util.List;
/**
 * User is an object, which contains all information about user.
 *
 * @author Sergei Levkovskii
 *
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userId;
    private UserRole role;
    private String name;
    private String eMail;
    private String login;
    private String password;
    private String blacklist;


    public User() {
    }

    public String getBlacklist() {
        return blacklist;
    }
    public void setBlacklist(String blacklist) {
        this.blacklist = blacklist;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {return userId;}
    public void setUserId(int userId) {this.userId = userId;}

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}


    public String geteMail() { return eMail; }
    public void seteMail(String eMail) { this.eMail = eMail;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (blacklist != user.blacklist) return false;
        if (role != user.role) return false;
        if (!name.equals(user.name)) return false;
        if (!eMail.equals(user.eMail)) return false;
        if (!login.equals(user.login)) return false;
        return password.equals(user.password);

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + role.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + eMail.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + blacklist.hashCode();
        return result;
    }
}
