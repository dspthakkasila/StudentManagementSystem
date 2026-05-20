<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Login Portal</title>

<link rel="stylesheet"
href="css/style.css">

</head>

<body>

<div class="login-container">

    <div class="login-card">

        <h1>Admin Login</h1>

<%
String error =
(String) request.getAttribute("error");

if(error != null){
%>

        <p class="error-text">

            <%= error %>

        </p>

<%
}
%>

        <!-- ADMIN LOGIN -->

        <form action="LoginServlet"
              method="post">

            <input type="text"
                   name="username"
                   placeholder="Enter Username"
                   required>

            <input type="password"
                   name="password"
                   placeholder="Enter Password"
                   required>

            <button type="submit">

                Admin Login

            </button>

        </form>

        <!-- FORGOT PASSWORD -->

        <a href="forgotPassword.jsp"
           class="forgot-link">

           Forgot Password?

        </a>

        <!-- DIVIDER -->

        <div class="divider">

            OR

        </div>

        <!-- STUDENT LOGIN -->

        <a href="studentLogin.jsp">

            <button class="student-login-btn">

                Student Login

            </button>

        </a>

    </div>

</div>

</body>
</html>