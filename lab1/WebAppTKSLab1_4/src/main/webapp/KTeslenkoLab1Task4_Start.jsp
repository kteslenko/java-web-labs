<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login in system</title>
</head>
<body>
<h1>Login in system</h1>
<form method="post" action="LoginCheckServlet">
    <label for="username">Username</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Password</label>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="Go to tasks">
</form>
</body>
</html>
