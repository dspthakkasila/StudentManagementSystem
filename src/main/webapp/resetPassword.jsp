<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Reset Password</title>

<link rel="stylesheet"
href="css/style.css">

</head>

<body>

<div class="form-container">

    <h1>Reset Password</h1>

    <form action="ResetPasswordServlet"
          method="post">

        <input type="password"
               name="password"
               placeholder="New Password"
               required>

        <button type="submit">

            Update Password

        </button>

    </form>

</div>

</body>
</html>