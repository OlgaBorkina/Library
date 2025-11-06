package college.configuration;

import college.model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static final SessionFactory sessionFactory;

    static{

        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class","org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url","jdbc:postgresql://localhost:5432/mydatabase");
        configuration.setProperty("hibernate.connection.username","admin");
        configuration.setProperty("hibernate.connection.password","admin");
        configuration.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");

        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "validate");


        configuration.addAnnotatedClass(Student.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
