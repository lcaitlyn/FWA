<%@ page import="edu.school21.cinema.models.User" %>
<%@ page import="edu.school21.cinema.repositories.LogsRepository" %>
<%@ page import="edu.school21.cinema.models.Log" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.format.FormatStyle" %>
<%@ page import="edu.school21.cinema.services.ImageService" %>
<%@ page import="java.util.Optional" %>
<%@ page import="edu.school21.cinema.models.Image" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <style>
        <%@include file="/WEB-INF/css/bootstrap.min.css"%>
        <%@include file="/WEB-INF/css/profile_style.css"%>
    </style>

    <title>Profile</title>
</head>
<body>
<div class="container">
    <div class="container__photo">

        <% User user = (User) request.getSession().getAttribute("user"); %>

        <img src="<c:url value='${avatar}'/>" alt="noname">
        <form method="POST" action="<%= request.getContextPath() %>/image" enctype="multipart/form-data">
            <input  class="form-control form-control-sm" type="file" accept="image/*" name="image">
            <button class="btn btn-outline-primary" type="submit">Upload</button>
        </form>
    </div>

    <div class="container__right-side">
        <div class="container__text">

            <p class="container__name"><%= user.getFirstName() + " " + user.getSecondName() %></p>
            <p class="container__email"><%= user.getEmail()%></p>
        </div>

        <table class="container__login-table table table-bordered">
            <tr>
                <th>Date</th>
                <th>Time</th>
                <th>IP</th>
            </tr>
            <%
                LogsRepository logsRepository = (LogsRepository) config.getServletContext().getAttribute("logsRepository");

                for (Log log : logsRepository.findAll(user.getEmail())) {
                    out.println("<tr>");

                    out.println("<td> " + log.getDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")) + "</td>");
                    out.println("<td> " + log.getDate().format(DateTimeFormatter.ofPattern("HH:mm")) + "</td>");
                    out.println("<td> " + log.getIp() + "</td>");

                    out.println("</tr>");
                }
            %>
        </table>
    </div>

    <table class="uploaded_photo table table-bordered">
        <tr>
            <th>File name</th>
            <th>Size</th>
            <th>MIME</th>
        </tr>
        <%
            List<Image> images = (List<Image>) request.getAttribute("images");

            for (Image image : images) {
                out.println("<tr>");

                String path = request.getContextPath() + "/image/imageid=" + image.getId();
                out.println("<td><a href=\"" + path + "\">" + image.getName() + "</td>");
                out.println("<td>" + image.getSize() + "</td>");
                out.println("<td>" + image.getMIME() + "</td>");

                out.println("</tr>");
            }
        %>
    </table>
</div>
</body>
</html>

