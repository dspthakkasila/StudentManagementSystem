<%@ page import="java.util.List"%>
<%@ page import="model.LeaveRequest"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Leave Requests</title>

<link rel="stylesheet"
href="css/style.css">

</head>

<body>

<div class="table-container">

    <h1>Leave Requests</h1>

    <table>

        <thead>

            <tr>

                <th>ID</th>

                <th>Student ID</th>

                <th>Reason</th>

                <th>From</th>

                <th>To</th>

                <th>Status</th>

                <th>Actions</th>

            </tr>

        </thead>

        <tbody>

<%

List<LeaveRequest> list =

(List<LeaveRequest>)
request.getAttribute(
        "leaveList");

if(list != null){

    for(LeaveRequest l : list){
%>

<tr>

    <td>
        <%= l.getId() %>
    </td>

    <td>
        <%= l.getStudentId() %>
    </td>

    <td>
        <%= l.getReason() %>
    </td>

    <td>
        <%= l.getFromDate() %>
    </td>

    <td>
        <%= l.getToDate() %>
    </td>

    <td>
        <%= l.getStatus() %>
    </td>

    <td>

        <a href=
"UpdateLeaveStatusServlet?id=<%= l.getId() %>&status=Approved">

            <button class="approve-btn">

                Approve

            </button>

        </a>

        <a href=
"UpdateLeaveStatusServlet?id=<%= l.getId() %>&status=Rejected">

            <button class="reject-btn">

                Reject

            </button>

        </a>

    </td>

</tr>

<%
    }
}
%>

        </tbody>

    </table>

</div>

</body>
</html>