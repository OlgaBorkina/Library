package library.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import library.dto.BookDTO;
import library.service.BookService;
import library.service.BookServiceImpl;

import java.util.List;

@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookController {

    private final BookService bookService = new BookServiceImpl();

    @POST
    @Path("/new")
    public Response addBook(BookDTO bookDto) {
        return bookService.addBook(bookDto);
    }
    @GET
    @Path("/{id}")
    public BookDTO getBookById(@PathParam("id") int id) {
        return bookService.getBookById(id);
    }

    @DELETE
    @Path("{id}")
    public boolean deleteBook(@PathParam("id") int id){
        return bookService.deleteBook(id);
    }
    @GET
    @Path(("/search/all"))
    public List<BookDTO> getAllBooks(){
        return bookService.getAllBooks();
    }
}
