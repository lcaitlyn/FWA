<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="/WEB-INF/css/bootstrap.min.css"%>

    .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
    }

    @media (min-width: 768px) {
        .bd-placeholder-img-lg {
            font-size: 3.5rem;
        }
    }

    .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
    }

    .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
    }

    .bi {
        vertical-align: -.125em;
        fill: currentColor;
    }

    .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
    }

    .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
    }

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