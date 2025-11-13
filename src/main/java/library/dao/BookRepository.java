package library.dao;

import library.model.Book;

import java.util.List;

public interface BookRepository {
    Book addBook(Book book);
    Book getBookById(int id);
    boolean deleteBookById(int id);
    List<Book> getAllBook();
}
