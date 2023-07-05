<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="/public/side-nav-style.css">
    <link rel="stylesheet" href="/public/xssInjection.css">

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
            <div class="progress-and-help">
                <div class="tooltip" id="xss-reflected-hints">Hints
                    <span class="tooltiptext" id="hint-text">Insert malicious HTML or JavaScript code</span>
                </div>
            </div>
            <div class="xss-reflected-container" >
                <form action="/xss/reflected/server-side/search-posts" method="get">
                    <h1 class="xss-reflected-title"> Search posts </h1>
                    <input class="xss-reflected-search-input" type="text" name="search">
                    <input class="xss-reflected-button" type="submit" value="Search">
                </form>
            </div>
            <p class="xss-reflected-title"> ${errorText}</p>
            <p class="xss-reflected-title">${errorTextProperty}</p>
        </main>
    </div>
</div>
</body>
</html>