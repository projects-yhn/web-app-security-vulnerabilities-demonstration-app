<%@ page import="java.util.List" %>
<%@ page import="com.yhn.webappcyberattacksdemoapp.xss.model.PostDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Posts</title>
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
                <form action="/xss/reflected/server-side/search-posts" method="get">
                    <label for="search-input"> Search post </label>
                    <input id="search-input" type="text" name="search">
                    <input type="submit" value="Search">
                </form>
            </div>
            <div>
                <%
                    List<PostDto> posts = (List<PostDto>) request.getAttribute("posts");
                    for (PostDto p : posts) { %>
                <div class="post-data-container">
                    <p><%=p.getTitle()%></p>
                    <p><%=p.getUserNames()%></p>
                    <p><%=p.getCreationTs()%></p
                    <hr>

                </div>
                <%}%>

            </div>
        </main>
    </div>
</div>
</body>
</html>