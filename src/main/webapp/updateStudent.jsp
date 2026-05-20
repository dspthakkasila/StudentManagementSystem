<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.Student" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Student</title>

<link rel="stylesheet" href="css/style.css">

</head>
<body>

<%
Student s = (Student) request.getAttribute("student");
%>

<div class="form-container">

    <h1>Update Student</h1>

    <form action="UpdateStudentServlet"
      method="post"
      enctype="multipart/form-data">

        <input type="hidden"
               name="id"
               value="<%= s.getId() %>">

        <input type="text"
               name="name"
               value="<%= s.getName() %>"
               required>

        <input type="email"
               name="email"
               value="<%= s.getEmail() %>"
               required>

        <input type="text"
               name="phone"
               value="<%= s.getPhone() %>"
               required>

        <input type="text"
               name="course"
               value="<%= s.getCourse() %>"
               required>

        <input type="number"
               step="0.01"
               name="marks"
               value="<%= s.getMarks() %>"
               required>
               
		<input type="file" name="photo">

        <button type="submit">
            Update Student
        </button>

    </form>

</div>

</body>
</html>