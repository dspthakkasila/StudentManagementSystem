package controller;

import util.EmailUtil;
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

@WebServlet("/UpdateStudentServlet")

@MultipartConfig

public class UpdateStudentServlet
extends HttpServlet {

	private static final long
	serialVersionUID = 1L;

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException,
					IOException {

		int id = Integer.parseInt(
				request.getParameter("id"));

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
						request.getParameter(
								"marks"));

		// PHOTO

		Part part =
				request.getPart("photo");

		String fileName =
				part.getSubmittedFileName();

		StudentDAOImpl dao =
				new StudentDAOImpl();

		Student s = new Student();

		s.setId(id);
		s.setName(name);
		s.setEmail(email);
		s.setPhone(phone);
		s.setCourse(course);
		s.setMarks(marks);

		// IF NEW PHOTO UPLOADED

		if(fileName != null &&
				!fileName.isEmpty()) {

			//            String uploadPath =
			//            getServletContext()
			//            .getRealPath("")
			//            + "uploads";

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

			s.setPhoto(fileName);

		} else {

			// KEEP OLD PHOTO

			Student oldStudent =
					dao.getStudentById(id);

			s.setPhoto(
					oldStudent.getPhoto());
		}

		boolean result =
				dao.updateStudent(s);

		if(result) {

			String body =

					"<html>"

		    + "<body style='font-family:Arial; background:#f4f4f4; padding:20px;'>"

		    + "<div style='max-width:600px; margin:auto; background:white; "
		    + "padding:30px; border-radius:10px;'>"

		    + "<h2 style='color:#0ea5e9;'>Student Details Updated</h2>"

		    + "<p>Hello <b>" + name + "</b>,</p>"

		    + "<p>Your student details were updated successfully.</p>"

		    + "<table style='width:100%; border-collapse:collapse;'>"

		    + "<tr>"
		    + "<td style='padding:10px; border:1px solid #ddd;'>Updated Course</td>"
		    + "<td style='padding:10px; border:1px solid #ddd;'>"
		    + course +
		    "</td>"
		    + "</tr>"

		    + "<tr>"
		    + "<td style='padding:10px; border:1px solid #ddd;'>Updated Marks</td>"
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

					"Student Details Updated",

					body);

			response.sendRedirect(
					"ViewStudentsServlet");

		} else {

			response.sendRedirect(
					"error.jsp");
		}
}
}