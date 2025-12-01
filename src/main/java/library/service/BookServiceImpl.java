package library.service;


import jakarta.ws.rs.core.Response;
import library.dao.BookRepository;
import library.dao.BookRepositoryImpl;
import library.dto.BookDTO;
import library.model.Book;
import library.utils.BookNotFoundException;
import library.utils.ErrorUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    private static final Logger logger = LogManager.getLogger();
    private final BookRepository bookRepository = new BookRepositoryImpl();

    @Override
    public Response addBook(BookDTO bookDto) {
        if (bookDto == null) {
            return Response.status(400).entity("Book is null").build();
        }
        Book book = new Book(
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPublishedYear(),
                bookDto.getGenre()
        );
        try {
            Book bookFromDb = bookRepository.addBook(book);
            BookDTO bookResp = new BookDTO(
                    bookFromDb.getId(),
                    bookFromDb.getTitle(),
                    bookFromDb.getAuthor(),
                    bookFromDb.getPublishedYear(),
                    bookFromDb.getGenre()
            );
            logger.info("Book added successfully with ID {}", bookResp.getId());
            return Response.ok(bookResp).build();
        } catch (Exception e) {
            logger.error("Error saving book", e);
            return ErrorUtils.buildError("Error saving book", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public BookDTO getBookById(int id) {
        try {
        Book book = bookRepository.getBookById(id);
        if (book == null) {
            logger.error("Book with id = {} not found", id);
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublishedYear(),
                book.getGenre());

    } catch(BookNotFoundException e){
        throw e;
    } catch(Exception e){
        logger.error("Unexpected error getting book by ID " + id, e);
        throw new RuntimeException("Internal error while retrieving book with ID " + id, e);
    }
}

@Override
public boolean deleteBook(int id) {
    boolean flag = bookRepository.deleteBookById(id);
    if (flag) {
        logger.info("Book with id = {} deleted successfully", id);
    } else {
        logger.error("Book with id = {} not found", id);
    }
    return flag;
}

@Override
public List<BookDTO> getAllBooks() {
    List<Book> books = bookRepository.getAllBook();
    List<BookDTO> booksDTO = new ArrayList<>();
    for (Book book : books) {
        BookDTO dto = new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublishedYear(),
                book.getGenre()
        );
        booksDTO.add(dto);
    }
    return booksDTO;
}
}
