package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBConnection;

@WebServlet("/ApplyLeaveServlet")

public class ApplyLeaveServlet
extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)

    throws ServletException,
    IOException {

        String studentId =
        request.getParameter(
        "studentId");

        String reason =
        request.getParameter(
        "reason");

        String fromDate =
        request.getParameter(
        "fromDate");

        String toDate =
        request.getParameter(
        "toDate");

        try {

            Connection con =
            DBConnection.getConnection();

            String query =

            "INSERT INTO leave_requests"
          + "(student_id,reason,"
          + "from_date,to_date,status)"
          + "VALUES(?,?,?,?,?)";

            PreparedStatement ps =
            con.prepareStatement(query);

            ps.setInt(1,
            Integer.parseInt(studentId));

            ps.setString(2,
            reason);

            ps.setString(3,
            fromDate);

            ps.setString(4,
            toDate);

            ps.setString(5,
            "Pending");

            ps.executeUpdate();

            ps.close();
            con.close();

            response.sendRedirect(
            "studentDashboard.jsp");

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}