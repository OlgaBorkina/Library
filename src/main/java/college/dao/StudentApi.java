package college.dao;

import college.model.Student;

import java.util.List;

public interface StudentApi {
    public boolean addStudent(Student student);

    public Student updateStudent(Integer id, Student student);

    public boolean deleteStudent(Integer id);

    public Student getStudent(int id);

    public List<Student> getAllStudents();
}
