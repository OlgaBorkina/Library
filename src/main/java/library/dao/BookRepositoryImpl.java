package library.dao;

import library.config.HibernateUtils;
import library.model.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Book addBook(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
            logger.info("Book added successfully: {}", book);
            return book;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public Book getBookById(int id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Book.class, id);
        }catch(Exception e) {
            logger.error("Error getting book with Id =" + id, e);
           throw e;
        }
    }

    @Override
    public boolean deleteBookById(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, id);
            if (book == null) {
                logger.error("Book with id {} not found", id);
            }
            session.remove(book);
            transaction.commit();
            logger.info("Book with id {} deleted", id);
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Book with id {} not found", id);
            throw e;
        }
    }

    @Override
    public List<Book> getAllBook() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Book",Book.class).list();
        }catch(Exception e) {
            logger.error("Error getting books list", e);
            return Collections.emptyList();
        }
    }
}
