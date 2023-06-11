<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>
    Welcome. Please Login.
    <form action="/process-login" method="POST">
        <div>
            <input type="text" name="username" placeholder="Username" id="username"/>
        </div>
        <div>
            <input type="password" name="password" placeholder="Password" id="password"/>
        </div>
        <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />

        <input type="submit" value="Log in" />
    </form>
</div>
</body>
</html>