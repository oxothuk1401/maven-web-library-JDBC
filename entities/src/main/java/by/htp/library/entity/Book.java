package by.htp.library.entity;

import java.util.List;

public class Book {
    private int idbooks = 0;
    private String access = null;
    private String author = null;
    private String title = null;
    private String date = null;
    private String location = null;
    ;
    private int amount = 0;
    private List<Book> listBooks = null;

    public Book() {

    }

    public Book(int idbooks, String access, String author, String title, String date, int amount) {
        this.idbooks = idbooks;
        this.access = access;
        this.author = author;
        this.title = title;
        this.date = date;
        this.amount = amount;
    }

    public int getIdbooks() {
        return idbooks;
    }

    public void setIdbooks(int idbooks) {
        this.idbooks = idbooks;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<Book> getListBooks() {
        return listBooks;
    }

    public void setListBooks(List<Book> listBooks) {
        this.listBooks = listBooks;
    }

    @Override
    public String toString() {
        return "Book{" +
                "idbooks=" + idbooks +
                ", access='" + access + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", amount=" + amount +
                ", listBooks=" + listBooks +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (idbooks != book.idbooks) return false;
        if (amount != book.amount) return false;
        if (!access.equals(book.access)) return false;
        if (!author.equals(book.author)) return false;
        if (!title.equals(book.title)) return false;
        if (!date.equals(book.date)) return false;
        if (!location.equals(book.location)) return false;
        return listBooks != null ? listBooks.equals(book.listBooks) : book.listBooks == null;

    }

    @Override
    public int hashCode() {
        int result = idbooks;
        result = 31 * result + access.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + amount;
        result = 31 * result + (listBooks != null ? listBooks.hashCode() : 0);
        return result;
    }
}
