<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<div>
    <form action="/xss/reflected/server-side/search-posts" method="get">
        <label for="search-input"> Search post </label>
        <input id="search-input" type="text" name="search">
        <input type="submit" value="Search">
    </form>
</div>
<p> ${errorText}</p>
${errorTextProperty}
</body>
</html>