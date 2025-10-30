package model;

import java.time.LocalDate;

public class BorrowedBooks {
    private int bookId;
    private String title;
    private String author;
    private String readerName;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowedBooks(int bookId, String title, String author, String readerName, LocalDate borrowDate, LocalDate returnDate) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.readerName = readerName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getReaderName() {
        return readerName;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        return "BorrowedBooks{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", readerName='" + readerName + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
