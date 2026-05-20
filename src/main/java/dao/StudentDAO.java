package dao;

import java.util.List;
import model.Student;

public interface StudentDAO {

    boolean addStudent(Student s);

    List<Student> getAllStudents();

    Student getStudentById(int id);

    boolean updateStudent(Student s);

    boolean deleteStudent(int id);
    
    List<Student> searchStudents(String keyword);
    
    List<Student> getStudentsByPage(int start, int total);

    int getStudentCount();
}