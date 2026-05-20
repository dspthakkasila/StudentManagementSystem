<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="model.Student"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Students</title>

<link rel="stylesheet" href="css/style.css">
<script src="js/search.js"></script>

</head>
<body>

	<div class="table-container">

		<!-- HEADER -->

		<div class="table-header">

			<h1>Student Records</h1>

			<!-- LIVE SEARCH -->

			<div class="search-form">

				<input type="text" id="searchInput"
					placeholder="Search by Name or Course" onkeyup="searchStudents()">

			</div>

			<a href="AdminDashboardServlet">

				<button class="home-btn">Home</button>

			</a>

		</div>

		<!-- DASHBOARD STATS -->

		<div class="stats-container">

			<div class="stat-card">

				<h2>Total Students</h2>

				<p>
					<%= request.getAttribute("totalStudents") %>
				</p>

			</div>

			<div class="stat-card">

				<h2>Average Marks</h2>

				<p>
					<%= request.getAttribute("avgMarks") %>
				</p>

			</div>

			<div class="stat-card">

				<h2>Highest Marks</h2>

				<p>
					<%= request.getAttribute("highestMarks") %>
				</p>

			</div>

		</div>

		<div class="top-toolbar">

			<!-- SEARCH -->

			<div class="toolbar-left">

				<input type="text" name="search" form="filterForm"
					placeholder="Search Student..."
					value="<%= request.getAttribute("search") != null ?
               request.getAttribute("search") : "" %>">

			</div>

			<!-- FILTER + SORT -->

			<form action="ViewStudentsServlet" method="get" id="filterForm"
				class="toolbar-right">

				<select name="course">

					<option value="">All Courses</option>

					<option value="Java Full Stack">Java Full Stack</option>

					<option value="Python Developer">Python Developer</option>

					<option value="MERN Stack Developer">MERN Stack Developer
					</option>

					<option value="Frontend Developer">Frontend Developer</option>

				</select> <select name="sort">

					<option value="">Sort By</option>

					<option value="nameAsc">Name A-Z</option>

					<option value="nameDesc">Name Z-A</option>

					<option value="marksHigh">Highest Marks</option>

					<option value="marksLow">Lowest Marks</option>

				</select>

				<button type="submit">Apply</button>

			</form>

		</div>

		<!-- TABLE -->

		<table>

			<thead>

				<tr>

					<th>ID</th>
					<th>Photo</th>
					<th>Name</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Course</th>
					<th>Marks</th>
					<th>Actions</th>

				</tr>

			</thead>

			<tbody id="studentTableBody">

				<%
List<Student> list =
(List<Student>) request.getAttribute("students");

if(list != null && !list.isEmpty()) {

	for(Student s : list) {
%>

				<tr>

					<td><%= s.getId() %></td>

					<td><img src="uploads/<%= s.getPhoto() %>" class="student-img">

					</td>

					<!--<td><%= s.getName() %></td>-->

					<td><a href="StudentProfileServlet?id=<%=s.getId()%>"
						class="profile-link"> <%=s.getName()%>

					</a></td>

					<td><%= s.getEmail() %></td>

					<td><%= s.getPhone() %></td>

					<td><%= s.getCourse() %></td>

					<td><%= s.getMarks() %></td>

					<td>
						<!-- EDIT --> <a href="EditStudentServlet?id=<%= s.getId() %>">

							<button class="edit-btn">Edit</button>

					</a> <!-- DELETE --> <a href="DeleteStudentServlet?id=<%= s.getId() %>"
						onclick="return confirm('Are you sure you want to delete this student?')">

							<button class="delete-btn">Delete</button>

					</a> 

					</td>

				</tr>

				<%
	}
}
else {
%>

				<tr>

					<td colspan="7">No Students Found</td>

				</tr>

				<%
}
%>

			</tbody>

		</table>

		<!-- PAGINATION -->

		<div class="pagination">

			<%
int currentPage =
(Integer)request.getAttribute(
        "currentPage");

int totalPages =
(Integer)request.getAttribute(
        "totalPages");

for(int i = 1; i <= totalPages; i++) {
%>

			<a href="ViewStudentsServlet?page=<%= i %>">

				<button
					class="page-btn
    <%= currentPage == i ?
    "active-page" : "" %>">

					<%= i %>

				</button>

			</a>

			<%
}
%>

		</div>

	</div>


</body>
</html>