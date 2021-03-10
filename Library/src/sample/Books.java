package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import sun.java2d.pipe.SpanShapeRenderer;

public class Books {
    /*Books (int bookId) {
        this.bookId = bookId;
    } */
    //private SimpleIntegerProperty bookId;
    private SimpleStringProperty bookTitle;
    //private SimpleIntegerProperty AuthorId;
    private int bookId;
    private int AuthorId;

    Books() {
        //this.bookId = new SimpleIntegerProperty();
        //this.AuthorId = new SimpleIntegerProperty();
        this.bookTitle = new SimpleStringProperty();
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle.get();
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle.set(bookTitle);
    }

    public int getAuthorId() {
        return this.AuthorId;
    }

    public void setAuthorId(int authorId) {
        this.AuthorId = authorId;
    }
}
