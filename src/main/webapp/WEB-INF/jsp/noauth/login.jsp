<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="/public/side-nav-style.css">
</head>
<body>

<div>
    <nav class="topnav">
        <ul>
            <li><a href="sss">Progress</a></li>
            <li><a href="ddd">Profiles</a></li>
            <li><a href="ddd">Settings</a></li>
        </ul>
    </nav>
    <div class="attack-demo-window">
        <nav class="sidebar">
            <ul class="attack-menu">
                <li><a href="/public/brokenAuthentication.html">Broken Authentication</a></li>
                <li><a href="/public/sqlInjectionUnion.html">SQL injection Union</a></li>
                <li><a href="/public/commandInjection.html">Command Injection</a></li>
                <li><a href="/public/pathTraversal.html"> Path Traversal</a></li>
                <li><a href="/xsrf/online-banking">CSRF/XSRF</a></li>
                <li><a href="/public/xssStored.html">XSS - Stored</a></li>
                <li><a href="/xss/reflected/server-side/search-posts"> XSS - Reflected</a></li>
                <li><a href="/public/xxeInjection.html">XXE Injection</a></li>
            </ul>
        </nav>
        <main class="web-attacks-container">
            <div>
                Welcome. Please Login.
                <form action="/process-login" method="POST">
                    <div>
                        <input type="text" name="username" placeholder="Username" id="username"/>
                    </div>
                    <div>
                        <input type="password" name="password" placeholder="Password" id="password"/>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <input type="submit" value="Log in"/>
                </form>
            </div>
        </main>
    </div>
</div>
</body>
</html>