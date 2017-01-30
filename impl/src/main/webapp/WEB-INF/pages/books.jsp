<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Books Page</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        table.text {
            width: 100%;
            border-spacing: 0;
        }

        table.text td {
            width: 50%;
            vertical-align: top;
        }

        td.rightcol {
            text-align: right;
        }
    </style>
</head>
<body>
<table class="text">
    <security:authorize access="! isAuthenticated()">
        <tr>
            <td><a href="<c:url value='/login'/>">Login</a></td>
            <td class="rightcol"><a href="<c:url value="/createNewUser"/>">Registration form</a></td>
        </tr>
    </security:authorize>
    <security:authorize access="isAuthenticated()">
        <tr>
            <td>
                <a href="<spring:url value='/logout'/>">Logout</a>
            </td>
        </tr>
    </security:authorize>
</table>
<label>
    Search book
    <form action="<c:url value='/search'/>" method="get">
        <input type="text" name="bookTitle" placeholder="Enter book name">
        <input type="submit" value="Find">
    </form>
</label>
<a href="<c:url value="/add"/>">Add new book</a>


<c:if test="${!empty listBooks}">
    <h1 align="center">Books list</h1>
    <table class="tg" align="center">
        <tr>
            <th width="30">ID</th>
            <th width="120">Title</th>
            <th width="120">Author</th>
            <th width="120">Year</th>
            <th width="120">Type</th>
            <th width="120">Download</th>
            <security:authorize access="isAuthenticated()">
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </security:authorize>
        </tr>
        <c:forEach items="${listBooks}" var="book">
            <tr>
                <td align="center">${book.bookID}</td>
                <td align="center">${book.bookTitle}</td>
                <td align="center">${book.author}</td>
                <td align="center">${book.year}</td>
                <td align="center">${book.bookType}</td>
                <td align="center"><a href="<c:url value='/download/${book.bookID}'/>" target="_blank">Download</a></td>
                <security:authorize access="isAuthenticated()">
                    <td align="center"><a href="<c:url value='/edit/${book.bookID}'/>">Edit</a></td>
                    <td align="center"><a href="<c:url value='/remove/${book.bookID}'/>">Delete</a></td>
                </security:authorize>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty listBooks}">
    <h1 align="center">No books found.</h1>
</c:if>
</body>
</html>