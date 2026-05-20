<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Student Login</title>

<link rel="stylesheet" href="css/style.css">

</head>

<body>

	<div class="form-container">

		<h1>Student Login</h1>

		<form action="StudentLoginServlet" method="post">

			<input type="email" name="email" placeholder="Enter Email" required>

			<input type="password" name="password" placeholder="Enter Password"
				required>

			<button type="submit">Login</button>

			<a href="studentRegister.jsp" class="forgot-link"> New Student?
				Register Here </a>

		</form>

	</div>

</body>
</html>