package controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAOImpl;
import util.EmailUtil;

@WebServlet("/ForgotPasswordServlet")

public class ForgotPasswordServlet
extends HttpServlet {

    private static final long
    serialVersionUID = 1L;

    protected void doPost(

            HttpServletRequest request,

            HttpServletResponse response)

            throws ServletException,
            IOException {

        String username =
                request.getParameter(
                        "username");

        AdminDAOImpl dao =
                new AdminDAOImpl();

        String email =
                dao.getAdminEmail(
                        username);

        if(email == null) {

            response.getWriter()
            .println("Invalid Username");

            return;
        }

        // OTP

        Random random =
                new Random();

        int otp =
                100000 +
                random.nextInt(900000);

        // SESSION

        HttpSession session =
                request.getSession();

        session.setAttribute(
                "otp", otp);

        session.setAttribute(
                "username",
                username);

        // EMAIL BODY

        String body =

        "<html>"

        + "<body style='font-family:Arial;'>"

        + "<h2>Password Reset OTP</h2>"

        + "<p>Your OTP is:</p>"

        + "<h1 style='color:#0ea5e9;'>"
        + otp +
        "</h1>"

        + "<p>Do not share this OTP.</p>"

        + "</body>"

        + "</html>";

        // SEND EMAIL

        EmailUtil.sendEmail(

                email,

                "Password Reset OTP",

                body);

        response.sendRedirect(
                "verifyOtp.jsp");
    }
}