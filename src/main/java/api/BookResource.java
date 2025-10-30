package api;

import dto.BookDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {
    List<BookDTO> books = new ArrayList<BookDTO>(){{
        add(new BookDTO(1,"book1", "author1",1945,"gender1"));
        add(new BookDTO(2,"book2", "author2",1978,"gender2"));
        add(new BookDTO(3,"book3", "author3",1989,"gender3"));
    }};

    @GET
    public List<BookDTO> getAllBooks() {
        return books;
    }

    @GET
    @Path("/seach")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response getBooksByAuthor(@QueryParam("author") String author) {
        if(author == null){
            return Response.status(400).entity("Author is null").build();
        }
        return Response.status(200).entity(books.stream().filter(c->c.getAuthor().equals(author)).collect(Collectors.toList())).build();
    }
    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public  Response addBook(BookDTO book) {
        if(book == null){
            return Response.status(400).entity("Book is null").build();
        }
        books.add(book);
        return Response.status(200).entity(book).build();
    }


}
