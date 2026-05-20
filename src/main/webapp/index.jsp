<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.Map"%>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

if (session.getAttribute("admin") == null) {

	response.sendRedirect("login.jsp");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Management System</title>

<link rel="stylesheet" href="css/style.css">

<script src="https://cdn.jsdelivr.net/npm/chart.js">
	
</script>

</head>
<body>

	<div class="admin-layout">

		<!-- SIDEBAR -->

		<div class="sidebar">

			<h2 class="logo">SMS Admin</h2>

			<a href="AdminDashboardServlet" class="side-link active-link">
				Dashboard </a> <a href="addStudent.jsp" class="side-link"> Add
				Student </a> <a href="ViewStudentsServlet" class="side-link"> Manage
				Students </a><a href="addNotification.jsp" class="side-link"> Send
				Notification </a> <a href="addTest.jsp" class="side-link"> Create
				Test </a> <a href="ViewLeaveServlet" class="side-link"> Leave
				Requests </a> <a href="LogoutServlet" class="side-link logout-side">
				Logout </a>

		</div>

		<!-- MAIN CONTENT -->

		<div class="main-content">

			<!-- TOPBAR -->

			<div class="topbar">

				<div>

					<h1>Admin Dashboard</h1>

					<p>Welcome Back Admin</p>

				</div>

			</div>

			<!-- STATS -->

			<div class="admin-stats">

				<div class="admin-card">

					<h3>Total Students</h3>

					<h1>
						<%=request.getAttribute("totalStudents")%>
					</h1>

				</div>

				<div class="admin-card">

					<h3>Average Marks</h3>

					<h1>
						<%=request.getAttribute("avgMarks")%>
					</h1>

				</div>

				<div class="admin-card">

					<h3>Highest Marks</h3>

					<h1>
						<%=request.getAttribute("highestMarks")%>
					</h1>

				</div>

				<div class="admin-card">

					<h3>Total Courses</h3>

					<h1>
						<%=request.getAttribute("totalCourses")%>
					</h1>

				</div>

			</div>

			<div class="admin-actions">

				<a href="addStudent.jsp" class="admin-action-card">

					<h2>➕ Add Student</h2>

					<p>Register new students</p>

				</a> <a href="ViewStudentsServlet" class="admin-action-card">

					<h2>📚 Manage Students</h2>

					<p>View and manage records</p>

				</a> <a href="addNotification.jsp" class="admin-action-card">

					<h2>🔔 Send Notification</h2>

					<p>Personal & Global Alerts</p>

				</a> <a href="addTest.jsp" class="admin-action-card">

					<h2>📝 Create Test</h2>

					<p>Schedule exams</p>

				</a>

			</div>

			<!-- CHART -->

			<div class="analytics-section">

				<div class="chart-container">

					<h2>Student Analytics</h2>

					<canvas id="dashboardChart" style="width: 100%; height: 400px;"></canvas>

				</div>

				<!-- Topper card -->
				<div class="topper-card">

					<h2>🏆 Top Performer</h2>

					<div class="topper-details">

						<img src="uploads/<%=request.getAttribute("topperPhoto")%>"
							class="topper-img">

						<h3>
							<%=request.getAttribute("topperName")%>
						</h3>

						<p>
							Course:
							<%=request.getAttribute("topperCourse")%>
						</p>

						<h1>
							<%=request.getAttribute("topperMarks")%>
						</h1>

						<span class="topper-badge"> Highest Marks </span>

					</div>

				</div>


			</div>

		</div>

	</div>

	<script>

const courseLabels = [

<%Map<String, Integer> pieData =

		(Map<String, Integer>) request.getAttribute("courseData");

if (pieData != null) {

	for (String course : pieData.keySet()) {%>

"<%=course%>",

<%}

}%>

];

const courseCounts = [

<%if (pieData != null) {

	for (Integer count : pieData.values()) {%>

<%=count%>,

<%}

}%>

];

console.log(courseLabels);
console.log(courseCounts);

const ctx =
document.getElementById(
"dashboardChart");

new Chart(ctx, {

    type:'pie',

    data:{

        labels:courseLabels,

        datasets:[{

            data:courseCounts,

            backgroundColor:[

                '#38bdf8',
                '#6366f1',
                '#10b981',
                '#f59e0b',
                '#ef4444',
                '#8b5cf6',
                '#14b8a6'

            ],

            borderWidth:1
        }]
    },

    options:{

        responsive:true,

        maintainAspectRatio:false,

        plugins:{

            legend:{

                labels:{

                    color:'white',

                    font:{

                        size:14
                    }
                }
            }
        }
    }
});

</script>

</body>
</html>