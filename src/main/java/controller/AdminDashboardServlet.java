package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAOImpl;
import model.Student;

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet
extends HttpServlet {

	private static final long
	serialVersionUID = 1L;

	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException,
					IOException {

		StudentDAOImpl dao =
				new StudentDAOImpl();

		// DASHBOARD DATA

		int totalStudents =
				dao.getStudentCount();

		double avgMarks =
				dao.getAverageMarks();

		double highestMarks =
				dao.getHighestMarks();

		int totalCourses =
				dao.getTotalCourses();

		// PIE CHART DATA

		Map<String,Integer> courseData =
				dao.getCourseWiseCount();

		//topper card
		Student topper =
				dao.getTopperStudent();

		// SEND TO JSP

		request.setAttribute(
				"totalStudents",
				totalStudents);

		request.setAttribute(
				"avgMarks",
				String.format("%.2f",
						avgMarks));

		request.setAttribute(
				"highestMarks",
				highestMarks);

		request.setAttribute(
				"totalCourses",
				totalCourses);

		request.setAttribute(
				"courseData",
				courseData);

		System.out.println(courseData);

		if(topper != null) {

			request.setAttribute(
					"topperName",
					topper.getName());

			request.setAttribute(
					"topperCourse",
					topper.getCourse());

			request.setAttribute(
					"topperMarks",
					topper.getMarks());

			request.setAttribute(
					"topperPhoto",
					topper.getPhoto());
		}

		// OPEN DASHBOARD

		request.getRequestDispatcher(
				"index.jsp")
		.forward(request,
				response);
	}
}