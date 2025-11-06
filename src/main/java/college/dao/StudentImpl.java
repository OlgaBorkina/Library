package college.dao;

import college.configuration.HibernateUtils;
import college.model.Student;
import org.hibernate.Transaction;

import java.util.List;

public class StudentImpl implements StudentApi {
    @Override
    public boolean addStudent(Student student) {
        Transaction transaction = null;
        var sessionFactory = HibernateUtils.getSessionFactory();
        try {
            var session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                e.printStackTrace();
                transaction.rollback();
            }
            throw new StudentException("Error adding student: " + student, e);
        }
    }

    @Override
    public Student updateStudent(Integer id, Student student) {
        Transaction transaction = null;
        Student studReturned = null;
        var sessionFactory = HibernateUtils.getSessionFactory();
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            studReturned = session.get(Student.class, id);
            if (studReturned == null) {
                throw new StudentException("Student with number =" + id + "not found", null);
            }

            if (student.getEmail() != null) {
                studReturned.setEmail(student.getEmail());
            }
            if (student.getName() != null) {
                studReturned.setName(student.getName());
            }
            session.flush();

        } catch (Exception e) {
            throw new StudentException("No changes were made to student number -" + id, e);
        }
        return studReturned;
    }

    @Override
    public boolean deleteStudent(Integer id) {
        Transaction transaction = null;
        var sessionFactory = HibernateUtils.getSessionFactory();
        try (var session = sessionFactory.openSession()) {
            Student stud = session.get(Student.class, id);
            transaction = session.beginTransaction();
            session.remove(stud);
            session.flush();
            return true;
        } catch (Exception e) {
            throw new StudentException("The student number " + id +" was not removed. -", e);
        }
    }

    @Override
    public Student getStudent(int id) {
        var sessionFactory = HibernateUtils.getSessionFactory();
        try(var session = sessionFactory.openSession()) {
            Student student = session.get(Student.class, id);
            if (student != null) {
                return student;
            }else{
                throw new StudentException("Student with number =" + id + " not found");
            }
        } catch (Exception e) {
            throw new StudentException("Error getting student with Id =" + id, e);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        var sessionFactory = HibernateUtils.getSessionFactory();
        try(var session = sessionFactory.openSession()) {
           List<Student> students = session.createQuery("from Student", Student.class).list();
            if (students.isEmpty()) {
                throw new StudentException("There are no students in the database");
            }
            return students;
        } catch (Exception e) {
            throw new StudentException("Error while getting list of students", e);
        }

    }
}
