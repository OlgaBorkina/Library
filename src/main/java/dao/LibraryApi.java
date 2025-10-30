package dao;

import model.Book;
import model.BorrowedBooks;
import model.Reader;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface LibraryApi {
    Book addBook (Book book) throws SQLException;
    Book updateStatusBook (int id, String status );
    Reader addReader (Reader reader);
   // List<BorrowedBooks> getAllBorrowed();
    Reader updateReader (Reader reader );
    List<Book> filterBooksByStatus (String status);
    List<Book> findBooksBorrowedByDate (LocalDate date);
}
