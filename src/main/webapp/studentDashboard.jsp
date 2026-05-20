<%@ page import="model.Student"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="model.Test"%>
<%@ page import="model.LeaveRequest"%>

<%
Student s =

		(Student) session.getAttribute("student");

if (s == null) {

	response.sendRedirect("studentLogin.jsp");

	return;
}
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Student Dashboard</title>

<link rel="stylesheet" href="css/style.css">

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>

<body>

	<div class="dashboard-container">

		<!-- TOPBAR -->

		<div class="dashboard-topbar">

			<h2>Student Dashboard</h2>

			<a href="LogoutServlet">

				<button class="logout-btn">Logout</button>

			</a> <a href="applyLeave.jsp">

				<button class="leave-btn">Apply Leave</button>

			</a>

		</div>

		<!-- PROFILE -->

		<div class="student-header">

			<img src="uploads/<%=s.getPhoto()%>" class="profile-image">

			<h1>

				<%=s.getName()%>

			</h1>

			<p>

				<%=s.getCourse()%>

			</p>

		</div>

		<!-- STATS -->

		<div class="stats-grid">

			<!--    <div class="detail-card">

            <h3>Student ID</h3>

            <p>

               s.getId()d() %>

            </p>

        </div> -->

			<div class="detail-card">

				<h3>Email</h3>

				<p>

					<%=s.getEmail()%>

				</p>

			</div>

			<div class="detail-card">

				<h3>Phone</h3>

				<p>

					<%=s.getPhone()%>

				</p>

			</div>

			<div class="detail-card">

				<h3>Marks</h3>

				<p>

					<%=s.getMarks()%>

				</p>

			</div>

			<div class="detail-card">

				<h3>Attendance</h3>

				<p>92%</p>

			</div>

		</div>

		<!-- ACTIONS -->

		<div class="student-actions">

			<a href="DownloadStudentPdfServlet?id=<%=s.getId()%>">

				<button class="pdf-btn">Download PDF</button>

			</a> <a href="DownloadIdCardServlet?id=<%=s.getId()%>">

				<button class="id-btn">Download ID Card</button>

			</a>

		</div>

		<!-- MAIN DASHBOARD GRID -->

		<div class="dashboard-sections">

			<!-- LEFT -->

			<div class="left-section">

				<!-- CHART -->

				<div class="chart-card" style="margin-top: 0px;">

					<h2>Performance Overview</h2>

					<canvas id="subjectChart"></canvas>

				</div>

				<!-- SKILLS -->

				<%
				List<String> skills = (List<String>) request.getAttribute("skills");
				%>

				<div class="skills-section">

					<h2>Skills</h2>

					<div class="skills-container">

						<%
						if (skills != null) {

							for (String skill : skills) {
						%>

						<span class="skill-badge"> <%=skill%>

						</span>

						<%
						}
						}
						%>

					</div>

				</div>

			</div>

			<!-- RIGHT -->

			<div class="right-section">

				<!-- ACHIEVEMENTS -->

				<%
				List<String> achievements = (List<String>) request.getAttribute("achievements");
				%>

				<div class="achievement-section">

					<h2>Achievements</h2>

					<%
					if (achievements != null) {

						for (String a : achievements) {
					%>

					<p class="achievement-item">

						<%=a%>

					</p>

					<%
					}
					}
					%>

				</div>

				<!-- NOTIFICATIONS -->

				<%
				List<String> notifications = (List<String>) request.getAttribute("notifications");
				%>

				<div class="notification-section">

					<h2>Notifications</h2>

					<%
					if (notifications != null) {

						for (String n : notifications) {
					%>

					<p class="notification-item">

						<%=n%>

					</p>

					<%
					}
					}
					%>

				</div>

				<%
				List<Test> tests = (List<Test>) request.getAttribute("tests");
				%>

				<div class="tests-section">

					<h2>Upcoming Tests</h2>

					<%
					if (tests != null) {

						for (Test t : tests) {
					%>

					<div class="test-card">

						<h3>
							<%=t.getSubject()%>
						</h3>

						<p>
							Date:
							<%=t.getTestDate()%>
						</p>

						<p>
							Total Marks:
							<%=t.getTotalMarks()%>
						</p>

					</div>

					<%
					}
					}
					%>

				</div>

				<!-- Leave Request -->

				<%
				List<LeaveRequest> leaves =

						(List<LeaveRequest>) request.getAttribute("leaves");
				%>

				<div class="leave-section">

					<h2>Leave History</h2>

					<%
					if (leaves != null) {

						for (LeaveRequest l : leaves) {
					%>

					<div class="leave-card">

						<h3>

							<%=l.getReason()%>

						</h3>

						<p>

							<%=l.getFromDate()%>

							to

							<%=l.getToDate()%>

						</p>

						<span class="leave-status"> <%=l.getStatus()%>

						</span>

					</div>

					<%
					}
					}
					%>

				</div>

			</div>

		</div>

	</div>

	<!-- CHART SCRIPT -->

	<script>

const labels = [

<%Map<String, Double> marksMap =

		(Map<String, Double>) request.getAttribute("subjectMarks");

if (marksMap != null) {

	for (String subject : marksMap.keySet()) {%>

"<%=subject%>",

<%}
}%>

];

const marks = [

<%if (marksMap != null) {

	for (Double m : marksMap.values()) {%>

<%=m%>,

<%}
}%>

];

const ctx =
document.getElementById(
"subjectChart");

new Chart(ctx, {

    type:'bar',

    data:{

        labels:labels,

        datasets:[{

            label:'Subject Marks',

            data:marks,

            backgroundColor:[

                '#38bdf8',
                '#8b5cf6',
                '#22c55e',
                '#f59e0b',
                '#ef4444'

            ],

            borderRadius:12,

            borderSkipped:false
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

                        size:16
                    }
                }
            }
        },

        scales:{

            x:{

                ticks:{

                    color:'white',

                    font:{

                        size:14
                    }
                },

                grid:{

                    color:
                    'rgba(255,255,255,0.05)'
                }
            },

            y:{

                beginAtZero:true,

                max:100,

                ticks:{

                    color:'white',

                    font:{

                        size:14
                    }
                },

                grid:{

                    color:
                    'rgba(255,255,255,0.05)'
                }
            }
        }
    }
});

</script>

</body>
</html>