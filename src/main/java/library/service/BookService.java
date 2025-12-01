package library.service;

import jakarta.ws.rs.core.Response;
import library.dto.BookDTO;

import java.util.List;

public interface BookService {
    Response addBook(BookDTO bookDto);
    BookDTO getBookById(int id);
    boolean deleteBook(int id);

    List<BookDTO> getAllBooks();
}
