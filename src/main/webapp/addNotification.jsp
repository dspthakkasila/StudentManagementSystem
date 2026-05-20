<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Add Notification</title>

<link rel="stylesheet" href="css/style.css">

</head>

<body>

<div class="form-container">

    <h1>Send Notification</h1>

    <form action="AddNotificationServlet"
    method="post">

        <label>Notification Type</label>

        <select name="type">

            <option value="global">
                Global
            </option>

            <option value="personal">
                Personal
            </option>

        </select>

        <label>Student ID
        (only for personal)</label>

        <input type="number"
        name="studentId">

        <label>Message</label>

        <textarea
        name="message"
        rows="5"
        required></textarea>

        <button type="submit">
            Send Notification
        </button>

    </form>

</div>

</body>
</html>