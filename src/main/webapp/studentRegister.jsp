<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Student Registration</title>

<link rel="stylesheet"
href="css/style.css">

</head>

<body>

<div class="form-container">

    <h1>Student Registration</h1>

    <form action="StudentRegisterServlet"
    method="post"
    enctype="multipart/form-data">

        <input type="text"
        name="name"
        placeholder="Enter Name"
        required>

        <input type="email"
        name="email"
        placeholder="Enter Email"
        required>

        <input type="text"
        name="phone"
        placeholder="Enter Phone"
        required>

        <input type="text"
        name="course"
        placeholder="Enter Course"
        required>

        <input type="number"
        step="0.01"
        name="marks"
        placeholder="Enter Marks"
        required>

        <input type="password"
        name="password"
        placeholder="Create Password"
        required>

        <input type="file"
        name="photo"
        required>

        <button type="submit">

            Register

        </button>

    </form>

    <a href="studentLogin.jsp">

        <button class="back-btn">

            Student Login

        </button>

    </a>

</div>

</body>
</html>