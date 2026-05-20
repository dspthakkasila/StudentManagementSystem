package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAOImpl;
import model.Student;

@WebServlet("/SearchStudentServlet")
public class SearchStudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String keyword =
                request.getParameter("keyword");

        StudentDAOImpl dao =
                new StudentDAOImpl();

        List<Student> list;

        if(keyword == null ||
           keyword.trim().isEmpty()) {

            list = dao.getAllStudents();

        } else {

            list = dao.searchStudents(keyword);
        }

        response.setContentType("text/html");

        PrintWriter out =
                response.getWriter();

        for(Student s : list) {

            out.println("<tr>");

            out.println("<td>" + s.getId() + "</td>");
            out.println("<td>" + s.getName() + "</td>");
            out.println("<td>" + s.getEmail() + "</td>");
            out.println("<td>" + s.getPhone() + "</td>");
            out.println("<td>" + s.getCourse() + "</td>");
            out.println("<td>" + s.getMarks() + "</td>");

            out.println("<td>");

            out.println(
            "<a href='EditStudentServlet?id="
            + s.getId() +
            "'><button class='edit-btn'>Edit</button></a>");

            out.println(
            "<a href='DeleteStudentServlet?id="
            + s.getId() +
            "' onclick=\"return confirm('Are you sure?')\">"
            +
            "<button class='delete-btn'>Delete</button></a>");

            out.println("</td>");

            out.println("</tr>");
        }
    }
}