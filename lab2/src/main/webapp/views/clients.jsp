<%@ page import="kteslenko.lab2.hibernate.ClientSort" %>
<%@ page import="kteslenko.lab2.hibernate.ClientFilter" %>
<%@ page import="kteslenko.lab2.entity.Gender" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%
    ClientFilter filter = (ClientFilter) request.getAttribute("filter");
    ClientSort sort = (ClientSort) request.getAttribute("sort");
%>

<c:set var="filter" value="<%= filter %>"/>
<c:set var="sort" value="<%= sort %>"/>

<html lang="en">

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clients</title>
</head>

<body>
<div id="container">
    <form class="data_form" action="clients" method="get">

        <h3>Sort</h3>

        <label for="sort_column">Column:</label>
        <select id="sort_column" name="sort_column">
            <c:forEach var="column" items="<%= ClientSort.Column.values() %>">
                <option value="${column}" ${sort.column.equals(column) ? "selected" : ""}>${column.displayName}</option>
            </c:forEach>
        </select>

        <label for="sort_direction">Direction:</label>
        <select id="sort_direction" name="sort_direction">
            <c:forEach var="direction" items="<%= ClientSort.Direction.values() %>">
                <option value="${direction}" ${sort.direction.equals(direction) ? "selected" : ""}>${direction.displayName}</option>
            </c:forEach>
        </select>

        <h3>Filters</h3>

        <label for="first_name_filter">First Name:</label>
        <input type="text" name="first_name_filter" id="first_name_filter" pattern="\p{L}*" value="${filter.firstName}">

        <label for="last_name_filter">Last Name:</label>
        <input type="text" name="last_name_filter" id="last_name_filter" pattern="\p{L}*" value="${filter.lastName}">

        <label for="phone_filter">Phone:</label>
        <input type="text" name="phone_filter" id="phone_filter" pattern="\+?\d*" value="${filter.phone}">

        <label for="email_filter">Email:</label>
        <input type="text" name="email_filter" id="email_filter" value="${filter.email}">

        <label>Gender:</label>
        <c:forEach var="gender" items="<%= Gender.values() %>">
            <div class="checkbox_row">
                <input type="checkbox" name="gender_filter" id="gender_filter_${gender}"
                       value="${gender}" ${filter.genders.contains(gender) ? "checked" : ""}>
                <label for="gender_filter_${gender}">${gender.displayName}</label>
            </div>
        </c:forEach>

        <label for="vip_filter">VIP:</label>
        <select id="vip_filter" name="vip_filter">
            <option ${filter.isVIP == null ? "selected" : ""}>All</option>
            <option ${filter.isVIP ? "selected" : ""}>True</option>
            <option ${filter.isVIP == false ? "selected" : ""}>False</option>
        </select>

        <button type="submit">Apply</button>
    </form>
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Gender</th>
            <th>Is VIP</th>
            <th colspan="2">Actions</th>
        </tr>

        <c:forEach var="client" items="${requestScope.clients}">
            <tr>
                <td>${client.firstName}</td>
                <td>${client.lastName}</td>
                <td>${client.phone}</td>
                <td>${client.email}</td>
                <td>${client.gender.displayName}</td>
                <td>${client.isVIP}</td>
                <td>
                    <form action="client/edit" method="get">
                        <input type="hidden" name="id" value="${client.id}">
                        <button type="submit">Edit</button>
                    </form>
                </td>
                <td>
                    <form action="client/delete" method="post">
                        <input type="hidden" name="id" value="${client.id}">
                        <button type="submit" onclick="return confirmation()">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="9">
                <form action="client/create" method="get">
                    <button type="submit">Create</button>
                </form>
            </td>
        </tr>
    </table>
</div>

<script>
    function confirmation() {
        return confirm('Are you sure you want to do this?');
    }
</script>
</body>

</html>
