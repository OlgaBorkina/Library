package college;

import college.configuration.HibernateUtils;
import college.dao.StudentException;
import college.dao.StudentImpl;
import college.model.Student;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentImpl studentImpl = new StudentImpl();
        try {
            studentImpl.addStudent(new Student("Olga", "olga@mail.com"));
            studentImpl.addStudent(new Student("Viki", "Kivi@mail.com"));
            studentImpl.addStudent(new Student("Mini", "Mini@mail.com"));
            System.out.println("Student added");
        } catch (StudentException e) {
            System.err.println("Failed to add student" + e.getMessage());
        }

        Student updated = studentImpl.updateStudent(1, new Student( "olgaaaa@mail.ruuuuu"));
        System.out.println(" After update: " + updated);

        Student studentGet = studentImpl.getStudent(2);
        System.out.println(studentGet);

        List<Student> students = studentImpl.getAllStudents();
        students.forEach(System.out::println);

        studentImpl.deleteStudent(3);

    }


}
