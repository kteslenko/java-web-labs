<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task1_4 - JSP+EL+JSTL</title>
</head>
<body>
<h1>Employees (JSP+EL+JSTL)</h1>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Birthday</th>
        <th>Gender</th>
        <th>Salary</th>
        <th>Programming Language</th>
    </tr>
    <c:forEach var="empl" items="${myList}">
        <tr>
            <td><c:out value="${empl.name}"></c:out></td>
            <c:if test="${empl.isYoung() == true}">
                <td style="background: green"><c:out value="${empl.getBirthdayAsString()}"></c:out></td>
            </c:if>
            <c:if test="${empl.isYoung() == false}">
                <td><c:out value="${empl.getBirthdayAsString()}"></c:out></td>
            </c:if>
            <c:if test="${empl.gender == true}">
                <td>male</td>
            </c:if>
            <c:if test="${empl.gender == false}">
                <td style="background: rosybrown">female</td>
            </c:if>
            <td>
                <fmt:setLocale value="uk_UA"/>
                <fmt:formatNumber value="${empl.salary}" type="currency"/>
            </td>
            <td>
                <c:if test="${empl.getProgrammingLanguage() == '.NET'}">
                    .NET
                </c:if>
                <c:if test="${empl.getProgrammingLanguage() == 'PHP'}">
                    PHP
                </c:if>
                <c:if test="${empl.getProgrammingLanguage() == 'Java'}">
                    Java
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<br><br>
<a href="KTeslenkoLab1Task4_Main.jsp">To START</a>
</body>
</html>
