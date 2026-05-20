<%@ page import="model.Student"%>

<%
Student s =

(Student)session.getAttribute(
        "student");

if(s == null){

    response.sendRedirect(
            "studentLogin.jsp");

    return;
}
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Apply Leave</title>

<link rel="stylesheet"
href="css/style.css">

</head>

<body>

<div class="form-container">

    <h1>Apply Leave</h1>

    <form action="ApplyLeaveServlet"
    method="post">

        <input type="hidden"
        name="studentId"
        value="<%= s.getId() %>">

        <label>Reason</label>

        <textarea
        name="reason"
        rows="5"
        required></textarea>

        <label>From Date</label>

        <input type="date"
        name="fromDate"
        required>

        <label>To Date</label>

        <input type="date"
        name="toDate"
        required>

        <button type="submit">

            Apply Leave

        </button>

    </form>

</div>

</body>
</html>