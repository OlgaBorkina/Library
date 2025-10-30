package dao;

import model.Book;
import model.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class LibraryImpl implements LibraryApi {
    private final DatabaseManager dbManager = new DatabaseManager();

       @Override
    public Book addBook(Book book) throws SQLException {
        String sql = "INSERT INTO library.books (title, author, published_year, genre) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection conn = dbManager.connectionManager();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, book.getTitle());
        stmt.setString(2, book.getAuthor());
        stmt.setInt(3, book.getPublishedYear());
        stmt.setString(4, book.getGenre());

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            book.setId(rs.getInt("id"));
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public Book updateStatusBook(int id, String status) {
        String sql = "UPDATE library.books SET genre = ? WHERE id = ? RETURNING *";
        Book updatedBook = new Book();
        try (Connection conn = dbManager.connectionManager();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                updatedBook.setId(rs.getInt("id"));
                updatedBook.setTitle(rs.getString("title"));
                updatedBook.setAuthor(rs.getString("author"));
                updatedBook.setPublishedYear(rs.getInt("published_year"));
                updatedBook.setGenre(rs.getString("genre"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updatedBook;
    }

    @Override
    public Reader addReader(Reader reader) {
        String sql = "INSERT INTO library.readers (name, email, phone) VALUES (?, ?, ?) RETURNING id";

        try (Connection conn = dbManager.connectionManager();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reader.getName());
            stmt.setString(2, reader.getEmail());
            stmt.setString(3, reader.getPhone());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                reader.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reader;
    }

//    @Override
//    public List<BorrowedBooks> getAllBorrowed() {
//        return List.of();
//    }

    @Override
    public Reader updateReader(Reader reader) {
        String sql = "UPDATE library.readers SET name = ?, email = ?, phone = ? WHERE id = ? RETURNING *";
        Reader updatedReader = null;

        try (Connection conn = dbManager.connectionManager();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reader.getName());
            stmt.setString(2, reader.getEmail());
            stmt.setString(3, reader.getPhone());
            stmt.setInt(4, reader.getId());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                updatedReader = new Reader();
                updatedReader.setId(rs.getInt("id"));
                updatedReader.setName(rs.getString("name"));
                updatedReader.setEmail(rs.getString("email"));
                updatedReader.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updatedReader;
    }

    @Override
    public List<Book> filterBooksByStatus(String status) {
        return List.of();
    }

    @Override
    public List<Book> findBooksBorrowedByDate(LocalDate date) {
        return List.of();
    }
}
