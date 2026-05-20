<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Create Test</title>

<link rel="stylesheet"
href="css/style.css">

</head>

<body>

<div class="form-container">

    <h1>Create Test</h1>

    <form action="AddTestServlet"
    method="post">

        <label>Test Type</label>

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

        <label>Subject</label>

        <input type="text"
        name="subject"
        required>

        <label>Test Date</label>

        <input type="date"
        name="testDate"
        required>

        <label>Total Marks</label>

        <input type="number"
        name="totalMarks"
        required>

        <button type="submit">
            Create Test
        </button>

    </form>

</div>

</body>
</html>