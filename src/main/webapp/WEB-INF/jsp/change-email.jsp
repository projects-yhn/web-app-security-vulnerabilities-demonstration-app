<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Email</title>
</head>
<body>
<div>
    Change Email
    <form action="/xsrf/email/change" method="POST">
        <div>
            <input type="text" name="email" placeholder="Email" id="email"/>
        </div>
<%--        <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />--%>

        <input type="submit" value="Change" />
    </form>
</div>
</body>
</html>