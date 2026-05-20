package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAOImpl;

@WebServlet("/ResetPasswordServlet")

public class ResetPasswordServlet
extends HttpServlet {

    private static final long
    serialVersionUID = 1L;

    protected void doPost(

            HttpServletRequest request,

            HttpServletResponse response)

            throws ServletException,
            IOException {

        String password =
                request.getParameter(
                        "password");

        HttpSession session =
                request.getSession();

        String username =
                (String) session.getAttribute(
                        "username");

        AdminDAOImpl dao =
                new AdminDAOImpl();

        boolean result =
                dao.updatePassword(
                        username,
                        password);

        if(result) {

            response.sendRedirect(
                    "login.jsp");

        } else {

            response.getWriter()
            .println(
                    "Password Update Failed");
        }
    }
}