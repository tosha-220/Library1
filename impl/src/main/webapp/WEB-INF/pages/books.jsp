<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<a href="../../index.jsp">Back to main menu</a>
<br/>
<br/>
<p></p>
<label>
    Search book
    <form action="/search" method="get">
        <input type="text" name="bookTitle" placeholder="Enter book name">
        <input type="submit" value="Find">
    </form>
</label>
<a href="<c:url value="/add"/>" target="_parent">Add new book</a>

<c:if test="${!empty listBooks}">
    <h1 align="center">Books List</h1>
    <table class="tg" align="center">
        <tr>
            <th width="80">ID</th>
            <th width="120">Title</th>
            <th width="120">Author</th>
            <th width="120">Year</th>
            <th width="120">Download</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>

        </tr>
        <c:forEach items="${listBooks}" var="book">
            <tr>
                <td align="center">${book.bookID}</td>
                <td align="center"><a href="/bookdata/${book.bookID}">${book.bookTitle}</a></td>
                <td align="center">${book.author}</td>
                <td align="center">${book.year}</td>
                <td align="center"><a href="<c:url value='/download/${book.hash}'/>">Download</a></td>
                <td align="center"><a href="<c:url value='/edit/${book.bookID}'/>">Edit</a></td>
                <td align="center"><a href="<c:url value='/remove/${book.bookID}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty listBooks}">
    <h1 align="center">No books in library. Please add.</h1>
</c:if>
</body>
</html>