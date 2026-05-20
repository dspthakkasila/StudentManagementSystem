package util;

import dao.StudentDAOImpl;
import model.Student;

public class TestStudent {

    public static void main(String[] args) {

        Student s = new Student();

        s.setName("Durga Sai");
        s.setEmail("durga@gmail.com");
        s.setPhone("9876543210");
        s.setCourse("Java Full Stack");
        s.setMarks(90);

        StudentDAOImpl dao = new StudentDAOImpl();

        boolean result = dao.addStudent(s);

        if(result) {
            System.out.println("Student Added Successfully");
        }
        else {
            System.out.println("Failed");
        }
    }
}