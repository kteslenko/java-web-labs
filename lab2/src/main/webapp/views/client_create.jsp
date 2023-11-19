<%@ page import="kteslenko.lab2.entity.Gender" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>">
    <title>Create Client</title>
</head>
<body>

<form id="client_form" class="data_form" action="<c:url value="/client/create"/>" method="post">
    <c:if test="${error != null}">
        <p class="error">${error}</p>
    </c:if>

    <label for="first_name">First Name:</label>
    <input type="text" name="first_name" id="first_name" pattern="\p{L}+" value="${client.firstName}" required>

    <label for="last_name">Last Name:</label>
    <input type="text" name="last_name" id="last_name" pattern="\p{L}+" value="${client.lastName}" required>

    <label for="phone">Phone:</label>
    <input type="text" name="phone" id="phone" pattern="\+\d{12}" value="${client.phone}" required>

    <label for="email">Email:</label>
    <input type="email" name="email" id="email" value="${client.email}" required>

    <label for="gender">Gender:</label>
    <select id="gender" name="gender" required>
        <c:forEach var="gender" items="<%= Gender.values() %>">
            <option value="${gender}" ${client.gender.equals(gender) ? "selected" : ""}>${gender.displayName}</option>
        </c:forEach>
    </select>

    <div class="checkbox_row">
        <input type="checkbox" name="vip" id="vip" value="true" ${client.isVIP ? "checked" : ""}>
        <label for="vip">VIP</label>
    </div>

    <div class="button_row">
        <button type="submit">Create</button>
        <a href="<c:url value="/clients"/>">
            <button type="button">Cancel</button>
        </a>
    </div>
</form>

</body>
</html>
