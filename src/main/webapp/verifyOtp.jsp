<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Verify OTP</title>

<link rel="stylesheet"
href="css/style.css">

</head>

<body>

<div class="form-container">

    <h1>Verify OTP</h1>

    <form action="VerifyOtpServlet"
          method="post">

        <input type="text"
               name="otp"
               placeholder="Enter OTP"
               required>

        <button type="submit">

            Verify OTP

        </button>

    </form>

</div>

</body>
</html>