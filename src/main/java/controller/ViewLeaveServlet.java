package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LeaveRequest;
import util.DBConnection;

@WebServlet("/ViewLeaveServlet")

public class ViewLeaveServlet
extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)

    throws ServletException,
    IOException {

        ArrayList<LeaveRequest> list =
        new ArrayList<>();

        try {

            Connection con =
            DBConnection.getConnection();

            String query =

            "SELECT * FROM leave_requests";

            PreparedStatement ps =
            con.prepareStatement(query);

            ResultSet rs =
            ps.executeQuery();

            while(rs.next()) {

                LeaveRequest l =
                new LeaveRequest();

                l.setId(
                rs.getInt("id"));

                l.setStudentId(
                rs.getInt("student_id"));

                l.setReason(
                rs.getString("reason"));

                l.setFromDate(
                rs.getString("from_date"));

                l.setToDate(
                rs.getString("to_date"));

                l.setStatus(
                rs.getString("status"));

                list.add(l);
            }

            request.setAttribute(
            "leaveList",
            list);

            request.getRequestDispatcher(
            "viewLeaves.jsp")
            .forward(request,response);

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}