package by.htp.library.entity;
/**
 * Book is an object, which contains all information about book.
 *
 * @author Sergei Levkovskii
 *
 */
public class Book {
    private long bookId;
    private String access;
    private String author;
    private String title;
    private String date;
    public Book(){

    }

    public Book(long bookId, String author, String title, String date) {
        this.bookId = bookId;
        this.author = author;
        this.title = title;
        this.date = date;
    }

    public long getBookId() {
        return bookId;
    }
    public void setBookId(long bookId) {
        this.bookId = bookId;
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

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", access='" + access + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (bookId != book.bookId) return false;
        if (access != null ? !access.equals(book.access) : book.access != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        return date != null ? date.equals(book.date) : book.date == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (access != null ? access.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
