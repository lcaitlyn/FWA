<%@ page import="edu.school21.cinema.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Профиль</title>
</head>
<body>
<h1>Профиль</h1>
<ul>
    <% User user = (User) request.getSession().getAttribute("user"); %>
    <li><%= user.getFirstName()%></li>
    <li><%= user.getSecondName()%></li>
    <li><%= user.getEmail()%></li>
</ul>
</body>
</html>
