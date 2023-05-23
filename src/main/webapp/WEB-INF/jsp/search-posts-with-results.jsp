<%@ page import="java.util.List" %>
<%@ page import="com.yhn.webappcyberattacksdemoapp.xss.model.PostDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Posts</title>
</head>
<body>
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
</body>
</html>