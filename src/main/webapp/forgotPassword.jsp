<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Forgot Password</title>

<link rel="stylesheet"
href="css/style.css">

</head>

<body>

<div class="form-container">

    <h1>Forgot Password</h1>

    <form action="ForgotPasswordServlet"
          method="post">

        <input type="text"
               name="username"
               placeholder="Enter Username"
               required>

        <button type="submit">

            Send OTP

        </button>

    </form>

</div>

</body>
</html>