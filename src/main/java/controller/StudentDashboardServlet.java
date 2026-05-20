package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentDAOImpl;
import model.LeaveRequest;
import model.Student;
import model.Test;

@WebServlet("/StudentDashboardServlet")

public class StudentDashboardServlet
extends HttpServlet {

	private static final long
	serialVersionUID = 1L;

	protected void doGet(

			HttpServletRequest request,

			HttpServletResponse response)

					throws ServletException,
					IOException {

		HttpSession session =
				request.getSession();

		Student student =
				(Student)session.getAttribute(
						"student");

		if(student == null) {

			response.sendRedirect(
					"studentLogin.jsp");

			return;
		}

		StudentDAOImpl dao =
				new StudentDAOImpl();

		// SKILLS

		List<String> skills =
				dao.getSkillsByStudentId(
						student.getId());

		// ACHIEVEMENTS

		List<String> achievements =
				dao.getAchievementsByStudentId(
						student.getId());

		// NOTIFICATIONS

		List<String> notifications =
				dao.getNotificationsByStudentId(
						student.getId());

		// Apply Leaves
		List<LeaveRequest> leaves =

				dao.getLeavesByStudentId(
						student.getId());

		request.setAttribute(
				"leaves",
				leaves);

		// AUTO PERFORMANCE NOTIFICATIONS

		if(student.getMarks() < 40){

			notifications.add(
					"Performance Warning: Your marks are low");
		}

		if(student.getMarks() >= 90){

			notifications.add(
					"Excellent Performance! Keep it up");
		}

		if(student.getMarks() >= 75
				&& student.getMarks() < 90){

			notifications.add(
					"Good Performance. Aim for 90+");
		}

		//test
		List<Test> tests =
				dao.getTestsByStudentId(
						student.getId());

		// SUBJECT MARKS

		Map<String,Double> subjectMarks =
				dao.getSubjectMarks(
						student.getId());

		// SEND TO JSP

		request.setAttribute(
				"skills",
				skills);

		request.setAttribute(
				"achievements",
				achievements);

		request.setAttribute(
				"notifications",
				notifications);

		request.setAttribute(
				"subjectMarks",
				subjectMarks);

		request.setAttribute(
				"tests",
				tests);

		// OPEN DASHBOARD

		request.getRequestDispatcher(
				"studentDashboard.jsp")
		.forward(
				request,
				response);


	}
}