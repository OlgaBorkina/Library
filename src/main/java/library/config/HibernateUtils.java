package library.config;

import library.model.Book;
import library.model.BorrowedBooks;
import library.model.Reader;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private final static SessionFactory sessionFactory;


    static {
        try{
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/mydatabase");
        configuration.setProperty("hibernate.connection.username", "admin");
        configuration.setProperty("hibernate.connection.password", "admin");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
        configuration.setProperty("hibernate.transaction.coordinator_class", "jdbc");
        configuration.setProperty("hibernate.current_session_context_class", "thread");


        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(Reader.class);
        configuration.addAnnotatedClass(BorrowedBooks.class);


        sessionFactory = configuration.buildSessionFactory();
    } catch (Exception e) {
            e.printStackTrace();
        throw new ExceptionInInitializerError("Failed to initialize Hibernate: " + e.getMessage());
    }
}

public static SessionFactory getSessionFactory() {
    return sessionFactory;
}
}