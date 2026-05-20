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

@WebServlet("/AddTestServlet")

public class AddTestServlet
extends HttpServlet {

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response)

					throws ServletException,
					IOException {

		String type =
				request.getParameter("type");

		String studentId =
				request.getParameter("studentId");

		String subject =
				request.getParameter("subject");

		String testDate =
				request.getParameter("testDate");

		int totalMarks =
				Integer.parseInt(
						request.getParameter(
								"totalMarks"));

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

					"INSERT INTO tests"
							+ "(student_id,subject,"
							+ "test_date,total_marks,type)"
							+ "VALUES(?,?,?,?,?)";

			PreparedStatement ps =
					con.prepareStatement(query);

			if(type.equals("global")) {

				ps.setNull(1,
						java.sql.Types.INTEGER);

			} else {

				ps.setInt(1,
						Integer.parseInt(studentId));
			}

			ps.setString(2,
					subject);

			ps.setString(3,
					testDate);

			ps.setInt(4,
					totalMarks);

			ps.setString(5,
					type);

			ps.executeUpdate();

			StudentDAOImpl dao =
					new StudentDAOImpl();

			// PERSONAL TEST EMAIL

			if(type.equals("personal")) {

				Student student =

						dao.getStudentById(
								Integer.parseInt(studentId));

				if(student != null) {

					String body =

							"<html>"

            		        + "<body style='font-family:Arial;'>"

            		        + "<h2 style='color:#0ea5e9;'>"
            		        + "New Test Scheduled"
            		        + "</h2>"

            		        + "<p>Hello "
            		        + student.getName()
            		        + ",</p>"

            		        + "<p>A new test has been created.</p>"

            		        + "<table style='border-collapse:collapse;'>"

            		        + "<tr>"
            		        + "<td style='padding:10px;border:1px solid #ccc;'>Subject</td>"
            		        + "<td style='padding:10px;border:1px solid #ccc;'>"
            		        + subject
            		        + "</td>"
            		        + "</tr>"

            		        + "<tr>"
            		        + "<td style='padding:10px;border:1px solid #ccc;'>Date</td>"
            		        + "<td style='padding:10px;border:1px solid #ccc;'>"
            		        + testDate
            		        + "</td>"
            		        + "</tr>"

            		        + "<tr>"
            		        + "<td style='padding:10px;border:1px solid #ccc;'>Total Marks</td>"
            		        + "<td style='padding:10px;border:1px solid #ccc;'>"
            		        + totalMarks
            		        + "</td>"
            		        + "</tr>"

            		        + "</table>"

            		        + "<br>"

            		        + "<p>Please prepare accordingly.</p>"

            		        + "<p>Student Management System</p>"

            		        + "</body>"

            		        + "</html>";

					EmailUtil.sendEmail(

							student.getEmail(),

							"New Test Scheduled",

							body);
				}
			}

			ps.close();
			con.close();

			response.sendRedirect(
					"addTest.jsp?success=true");

		} catch(Exception e) {

			e.printStackTrace();
		}
	}
}