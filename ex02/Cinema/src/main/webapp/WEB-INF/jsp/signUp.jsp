<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="/WEB-INF/css/bootstrap.min.css"%>



    <%@include file="/WEB-INF/css/sign_style.css"%>
</style>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
    <title>Registration</title>
</head>
<body class="text-center">
<main class="form-signin w-100 m-auto">
    <form enctype="multipart/form-data" method="post">
        <h1 class="h3 mb-3 fw-normal">Sign Up</h1>
        <div class="form-floating">
            <input name="firstname" type="text" class="form-control" id="inputFirstName" placeholder="Andrey">
            <label for="floatingInput">First Name</label>
        </div>
        <div class="form-floating">
            <input name="secondname" type="text" class="form-control" id="inputSecondName" placeholder="Sidorov">
            <label for="floatingInput">Second Name</label>
        </div>
        <div class="form-floating">
            <input name="email" type="email" class="form-control" id="floatingInput" placeholder="andreysidorov228@mail.ru">
            <label for="floatingInput">Email address</label>
        </div>
        <div class="form-floating">
            <input name="password" type="password" class="form-control" id="floatingPassword" placeholder="andrey123">
            <label for="floatingPassword">Password</label>
        </div>

        <button name="signIn" class="w-100 btn btn-lg btn-primary" type="submit">Sign up</button>
    </form>
</main>
</body>
</html>
