package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.StudentDAOImpl;
import model.Student;

@WebServlet("/StudentRegisterServlet")

@MultipartConfig

public class StudentRegisterServlet
extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)

    throws ServletException,
    IOException {

        String name =
        request.getParameter("name");

        String email =
        request.getParameter("email");

        String phone =
        request.getParameter("phone");

        String course =
        request.getParameter("course");

        double marks =
        Double.parseDouble(
        request.getParameter("marks"));

        String password =
        request.getParameter("password");

        // PHOTO

        Part part =
        request.getPart("photo");

        String fileName =
        part.getSubmittedFileName();

        String uploadPath =
        getServletContext()
        .getRealPath("/uploads");

        File uploadDir =
        new File(uploadPath);

        if(!uploadDir.exists()) {

            uploadDir.mkdir();
        }

        part.write(
        uploadPath
        + File.separator
        + fileName);

        // SET DATA

        Student s =
        new Student();

        s.setName(name);

        s.setEmail(email);

        s.setPhone(phone);

        s.setCourse(course);

        s.setMarks(marks);

        s.setPassword(password);

        s.setPhoto(fileName);

        StudentDAOImpl dao =
        new StudentDAOImpl();

        boolean result =
        dao.addStudent(s);

        if(result) {

            response.sendRedirect(
            "studentLogin.jsp");

        } else {

            response.sendRedirect(
            "error.jsp");
        }
    }
}