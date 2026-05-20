<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.Student"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Student Profile</title>

<link rel="stylesheet" href="css/style.css">

</head>

<body>

	<%
Student s =
(Student)request.getAttribute(
        "student");
%>

	<div class="profile-container">

		<!-- LEFT SIDE -->

		<div class="profile-left">

			<img src="uploads/<%= s.getPhoto() %>" class="profile-image">

			<h2><%= s.getName() %></h2>

			<p><%= s.getCourse() %></p>

		</div>

		<!-- RIGHT SIDE -->

		<div class="profile-right">

			<h1>Student Profile</h1>

			<!-- DETAILS -->

			<div class="profile-details">

				<div class="detail-card">

					<h3>Email</h3>

					<p><%= s.getEmail() %></p>

				</div>

				<div class="detail-card">

					<h3>Phone</h3>

					<p><%= s.getPhone() %></p>

				</div>

				<div class="detail-card">

					<h3>Marks</h3>

					<p><%= s.getMarks() %></p>

				</div>

				<div class="detail-card">

					<h3>Attendance</h3>

					<p>92%</p>

				</div>

			</div>

			<!-- CHART -->

			<div class="chart-card">

				<h2>Performance Chart</h2>

				<!-- HIDDEN VALUE -->

				<input type="hidden" id="marksValue" value="<%= s.getMarks() %>">

				<canvas id="marksChart"></canvas>

			</div>

			<!-- ACTIONS -->

			<div class="profile-actions">

				<a href="EditStudentServlet?id=<%= s.getId() %>">

					<button class="edit-btn">Edit Profile</button>

				</a> <a href="ViewStudentsServlet">

					<button class="back-btn">Back</button>

				</a>

				<!-- PDF -->
				<a href="DownloadStudentPdfServlet?id=<%= s.getId() %>">

					<button class="pdf-btn">PDF</button>

				</a>

				<!-- ID CARD -->
				<a href="DownloadIdCardServlet?id=<%=s.getId()%>">

					<button class="id-btn">ID Card</button>

				</a>

			</div>

		</div>

	</div>

	<!-- CHART JS -->

	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

	<!-- CUSTOM JS -->

	<script src="js/profile.js"></script>

</body>

</html>