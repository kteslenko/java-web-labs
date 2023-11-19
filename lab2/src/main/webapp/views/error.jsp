<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
</head>
<body>

<div id="error_page_container">
    <div id="error_container">
        <h1>${error}</h1>

        <a href="<c:url value="/clients"/>">Starting Page</a>
    </div>
</div>

</body>
</html>
