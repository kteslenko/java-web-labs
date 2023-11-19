<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kteslenko.webapptkslab1_4.entity.EmployeeList" %>
<%@ page import="kteslenko.webapptkslab1_4.entity.Employee" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee List</title>
</head>
<body>
<h1>Employees JSP</h1>
<br>
<a href="KTeslenkoLab1Task4_Main.jsp">To START</a>
<br>
<table>
    <tr>
        <th>Name</th>
        <th>Birthday</th>
        <th>Gender</th>
        <th>Salary</th>
        <th>Programming Language</th>
    </tr>
    <%
        for(Employee e : (EmployeeList) request.getAttribute("myList")){
    %>
    <tr>
        <td><%= e.getName() %></td>
        <td><%= e.getBirthdayAsString() %></td>
        <td><%= e.getGender() %></td>
        <td><%= e.getSalaryCurrency() %></td>
        <td><%= e.getProgrammingLanguage() %></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
