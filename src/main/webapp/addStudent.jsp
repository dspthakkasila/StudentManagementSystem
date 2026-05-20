<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Add Student</title>

<link rel="stylesheet" href="css/style.css">

</head>

<body>

	<div class="form-container">

		<h1>Add Student</h1>

		<form action="AddStudentServlet" method="post"
			enctype="multipart/form-data">

			<input type="text" name="name" placeholder="Enter Name" required>

			<input type="email" name="email" placeholder="Enter Email" required>

			<input type="text" name="phone" placeholder="Enter Phone" required>

			<input type="text" name="course" placeholder="Enter Course" required>

			<input type="number" step="0.01" name="marks"
				placeholder="Enter Marks" required>

			<!-- PASSWORD FIELD -->

			<input type="password" name="password" placeholder="Enter Password"
				required>


			<!-- PHOTO -->

			<input type="file" name="photo" required>

			<button type="submit">Save Student</button>

		</form>

		<a href="AdminDashboardServlet">

			<button class="back-btn">Back</button>

		</a>

	</div>

</body>
</html>