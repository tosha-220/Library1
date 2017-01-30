<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1 align="center">
    Error!
</h1>
<p align="center">
    User with login <strong>${user.login}</strong> or email <strong>${user.email}</strong> is already exists.
    <br><a href="<c:url value='/books'/>">To main page</a><br>
</p>
</body>
</html>
