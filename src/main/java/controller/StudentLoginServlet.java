package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import dao.StudentDAOImpl;
import model.Student;

@WebServlet("/StudentLoginServlet")

public class StudentLoginServlet
extends HttpServlet {

    private static final long
    serialVersionUID = 1L;

    protected void doPost(

            HttpServletRequest request,

            HttpServletResponse response)

            throws ServletException,
            IOException {

        String email =
                request.getParameter(
                        "email");

        String password =
                request.getParameter(
                        "password");

        StudentDAOImpl dao =
                new StudentDAOImpl();

        Student student =

        dao.studentLogin(
                email,
                password);

        if(student != null) {

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "student",
                    student);

            response.sendRedirect(
                    "StudentDashboardServlet");
        } else {

            response.getWriter()
            .println("Invalid Credentials");
        }
    }
}