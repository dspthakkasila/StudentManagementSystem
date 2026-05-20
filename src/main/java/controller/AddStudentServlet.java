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
import util.EmailUtil;

@WebServlet("/AddStudentServlet")

@MultipartConfig

public class AddStudentServlet
extends HttpServlet {

	private static final long
	serialVersionUID = 1L;

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
		
		String password =
				request.getParameter(
				        "password");

		double marks =
				Double.parseDouble(
						request.getParameter(
								"marks"));

		// FILE UPLOAD

		Part part =
				request.getPart("photo");

		String fileName =
				part.getSubmittedFileName();
		System.out.println(fileName);

		//        String uploadPath =
		//        getServletContext()
		//        .getRealPath("")
		//        + "uploads";

		String uploadPath =
				getServletContext().getRealPath("/uploads");

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

		Student s = new Student();

		s.setName(name);
		s.setEmail(email);
		s.setPhone(phone);
		s.setCourse(course);
		s.setMarks(marks);

		s.setPhoto(fileName);
		s.setPassword(password);

		StudentDAOImpl dao =
				new StudentDAOImpl();

		boolean result =
				dao.addStudent(s);

		if(result) {

			String body =

					"<html>"

            + "<body style='font-family:Arial; background:#f4f4f4; padding:20px;'>"

            + "<div style='max-width:600px; margin:auto; background:white; "
            + "padding:30px; border-radius:10px;'>"

            + "<h2 style='color:#0ea5e9;'>Student Registration Successful</h2>"

            + "<p>Hello <b>" + name + "</b>,</p>"

            + "<p>Your registration was completed successfully.</p>"

            + "<table style='width:100%; border-collapse:collapse;'>"

            + "<tr>"
            + "<td style='padding:10px; border:1px solid #ddd;'>Course</td>"
            + "<td style='padding:10px; border:1px solid #ddd;'>"
            + course +
            "</td>"
            + "</tr>"

            + "<tr>"
            + "<td style='padding:10px; border:1px solid #ddd;'>Marks</td>"
            + "<td style='padding:10px; border:1px solid #ddd;'>"
            + marks +
            "</td>"
            + "</tr>"

            + "</table>"

            + "<br>"

            + "<p style='color:gray;'>Thank you,<br>"
            + "Student Management System</p>"

            + "</div>"

            + "</body>"

            + "</html>";

			EmailUtil.sendEmail(

					email,

					"Student Registration Successful",

					body);

			response.sendRedirect(
			        "AdminDashboardServlet");

		} else {

			response.sendRedirect(
					"error.jsp");
		}
	}
}