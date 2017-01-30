<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration form</title>
    <script language="JavaScript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.0/jquery.min.js"></script>
    <script>
        function check() {
            if (($('#login').val() != '') && ($('#password').val() != '') && ($('#firstName').val() != '') &&
                ($('#email').val() != '') && ($('#secondName').val() != '') && ($('#city').val() != '')) {
                $('#submit').removeAttr('disabled');
            }
            else {
                $('#submit').attr('disabled', 'disable');
            }
        }
    </script>
</head>
<body>
<c:url var="createUser" value="/createUser"/>
<form:form method="post" action="${createUser}" commandName="user">
    <table align="center">
        <tr>
            <td>
                <form:label path="login">
                    <spring:message text="Login"/>
                </form:label>
            </td>
            <td>
                <form:input path="login" onkeyup="check();" id="login"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password">
                    <spring:message text="Password"/>
                </form:label>
            </td>
            <td>
                <form:input path="password" onkeyup="check();" id="password"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="email">
                    <spring:message text="Email"/>
                </form:label>
            </td>
            <td>
                <form:input path="email" onkeyup="check();" id="email"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="firstName">
                    <spring:message text="First name"/>
                </form:label>
            </td>
            <td>
                <form:input path="firstName" onkeyup="check();" id="firstName"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="secondName">
                    <spring:message text="Second name"/>
                </form:label>
            </td>
            <td>
                <form:input path="secondName" onkeyup="check();" id="secondName"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="city">
                    <spring:message text="City"/>
                </form:label>
            </td>
            <td>
                <form:input path="city" onkeyup="check();" id="city"/>
            </td>
        </tr>
        <tr>
            <td>
                <form>
                    <input type="submit" id="submit" disabled="disabled"
                           value="<spring:message text="Registration"/>"/>
                </form>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
