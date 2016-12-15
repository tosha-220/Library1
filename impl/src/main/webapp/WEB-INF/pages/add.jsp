<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<title>Books Page</title>
<head>
    <script language="JavaScript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.0/jquery.min.js"></script>
    <script>
        function check() {
            if (($('#file').val() != '') && ($('#bookTitle').val() != '') && ($('#author').val() != '') && ($('#year').val() != ''))
                $('#button').removeAttr('disabled');
            else
                $('#button').attr('disabled', 'disable');
        }
    </script>
    <h1>Add a Book</h1>
</head>
<body>
<c:url var="addAction" value="/upload"/>

<form:form method="post" enctype="multipart/form-data" action="${addAction}" commandName="book">
    <table>
        <tr>
            <td>
                <form:label path="bookTitle">
                    <spring:message text="Title"/>
                </form:label>
            </td>
            <td>
                <form:input path="bookTitle" onkeyup="check();" id="bookTitle"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="author">
                    <spring:message text="Author"/>
                </form:label>
            </td>
            <td>
                <form:input path="author" onkeyup="check();" id="author"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="year">
                    <spring:message text="Year"/>
                </form:label>
            </td>
            <td>
                <form:input path="year" onkeyup="check();" id="year"/>
            </td>
        </tr>
    </table>
    <tr>
        <td>
            <form>
                File to upload: <input type="file" onchange="check();" id="file"
                                       accept="application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                                       name="file">
                <p><input type="submit" id="button" disabled="disabled"
                          value="<spring:message text="Add Book"/>"/></p>
            </form>
        </td>
    </tr>
</form:form>

</body>
</html>
