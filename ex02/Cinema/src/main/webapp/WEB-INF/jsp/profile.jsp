<%@ page import="edu.school21.cinema.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
        <% String logo = "/Users/lcaitlyn/Desktop/FWA/ex02/Cinema/src/main/webapp/WEB-INF/logo/avatar.jpeg"; %>
        <img src="<%=logo%>>" alt="noname">
        <!--  here style bootstrap class - @btn@-->
        <form method="POST">
            <input  class="form-control form-control-sm" type="file" name="" id="" pa>
            <button class="btn btn-outline-primary" type="button">Upload</button>
        </form>
    </div>

    <div class="container__right-side">
        <div class="container__text">
            <p class="container__name"> It's me</p>
            <p class="container__email">java@senior.ru</p>
        </div>

        <table class="container__login-table table table-bordered">
            <tr>
                <th>Date</th>
                <th>Time</th>
                <th>IP</th>
            </tr>
            <tr>
                <td>December 10, 2020</td>
                <td>05:00</td>
                <td>127.0.01</td>
            </tr>
            <tr>
                <td>December 10, 2020</td>
                <td>05:00</td>
                <td>127.0.01</td>
            </tr>
            <tr>
                <td>December 10, 2020</td>
                <td>05:00</td>
                <td>127.0.01</td>
            </tr>
        </table>
    </div>


    <table class="uploaded_photo table table-bordered">
        <tr>
            <th>File name</th>
            <th>Size</th>
            <th>MIME</th>
        </tr>
        <tr>
            <td>avatar.jpg</td>
            <td>196KB</td>
            <td>image/jpg</td>
        </tr>
        <tr>
            <td>image.png</td>
            <td>1MB</td>
            <td>image/png</td>
        </tr>
    </table>
</div>
</body>
</html>

