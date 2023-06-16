<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Email</title>
    <link rel="stylesheet" href="/public/side-nav-style.css">
</head>
<body>
<div>
    <nav class="topnav">
        <ul>
            <li><a href="sss">Ala Bala</a></li>
            <li><a href="ddd">Profiles</a></li>
            <li><a href="ddd">Stats</a></li>
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
                <li><a href="/public/xssReflected.html"> XSS - Reflected</a></li>
                <li><a href="/public/xxeInjection.html">XXE Injection</a></li>
            </ul>
        </nav>
        <main class="web-attacks-container">
            <div>
                Change Email
                <form action="/xsrf/email/change" method="POST">
                    <div>
                        <input type="text" name="email" placeholder="Email" id="email"/>
                    </div>
                    <%--        <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />--%>

                    <input type="submit" value="Change"/>
                </form>
            </div>
        </main>
    </div>
</div>

</body>
</html>