<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%@ include file="menu.jsp"%>
<div class="container">
    <h1><%= "Hello, Page With Table!" %></h1>
    <br><br>
    <h3>Teslenko Kostiantyn Serhiyovich</h3>
    <br><br>
    <p>Today: <%= java.time.LocalDate.now() %></p>
</div>
<br><br>
<%@ include file="footer.jsp"%>
</body>
</html>
