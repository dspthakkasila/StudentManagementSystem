package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAOImpl;
import model.Student;
import util.EmailUtil;
import util.DBConnection;

@WebServlet("/AddNotificationServlet")

public class AddNotificationServlet
extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)

    throws ServletException,
    IOException {

        String type =
        request.getParameter("type");

        String message =
        request.getParameter("message");

        String studentId =
        request.getParameter("studentId");

        try {

            Connection con =
            DBConnection.getConnection();
            
            if(type.equals("personal") &&
            		(studentId == null ||
            		studentId.isEmpty())) {

            		    response.getWriter().println(
            		    "Student ID Required");

            		    return;
            		}
            
            String query =

            "INSERT INTO notifications"
          + "(student_id,message,type)"
          + "VALUES(?,?,?)";

            PreparedStatement ps =
            con.prepareStatement(query);

            if(type.equals("global")) {

                ps.setNull(
                1,
                java.sql.Types.INTEGER);

            } else {

                ps.setInt(
                1,
                Integer.parseInt(studentId));
            }

            ps.setString(2, message);

            ps.setString(3, type);

            ps.executeUpdate();
            
            StudentDAOImpl dao =
            		new StudentDAOImpl();

            		if(type.equals("personal")) {

            		    Student student =

            		    dao.getStudentById(
            		    Integer.parseInt(studentId));

            		    if(student != null) {

            		        String body =

            		        "<html>"

            		        + "<body style='font-family:Arial;'>"

            		        + "<h2 style='color:#0ea5e9;'>"
            		        + "New Notification"
            		        + "</h2>"

            		        + "<p>Hello "
            		        + student.getName()
            		        + ",</p>"

            		        + "<p>You received a new notification.</p>"

            		        + "<div style='padding:15px;"
            		        + "background:#f4f4f4;"
            		        + "border-radius:8px;'>"

            		        + message

            		        + "</div>"

            		        + "<br>"

            		        + "<p>Student Management System</p>"

            		        + "</body>"

            		        + "</html>";

            		        EmailUtil.sendEmail(

            		        student.getEmail(),

            		        "New Notification",

            		        body);
            		    }
            		}
            
            ps.close();
            con.close();

            response.sendRedirect(
            "addNotification.jsp?success=true");

        } catch(Exception e) {

            e.printStackTrace();
        } 
    }
}