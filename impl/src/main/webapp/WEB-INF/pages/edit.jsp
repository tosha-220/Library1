<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<title>Books Page</title>


<h1>Edit a Book</h1>


<c:url var="edit" value="/update"/>

<form:form action="${edit}" commandName="book">
    <table>
        <tr>
            <td>
                <form:hidden path="bookID"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="bookTitle">
                    <spring:message text="Title"/>
                </form:label>
            </td>
            <td>
                <form:input path="bookTitle"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="author">
                    <spring:message text="Author"/>
                </form:label>
            </td>
            <td>
                <form:input path="author"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="year">
                    <spring:message text="Year"/>
                </form:label>
            </td>
            <td>
                <form:input path="year"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:hidden path="link"/>
            </td>
        </tr>
        <tr>
            <td>

                <form:hidden path="mimeType"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:hidden path="hash"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit"
                       value="<spring:message text="Edit Book"/>"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>