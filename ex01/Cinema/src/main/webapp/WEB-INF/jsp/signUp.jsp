<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
    <h1>Регистрация</h1>
    <form enctype="multipart/form-data" method="post">
        <label>Имя: <input name="firstname" type="text" value="${firstname}"></label><br>
        <label>Фамилия: <input name="secondname" type="text" value="${secondname}"></label><br>
        <label>Электронная почта: <input name="email" type="email" value="${email}"></label><br>
        <label>Пароль: <input name="password" type="password" value="${firstname}"></label><br>
        <label>
            <input type="submit" name="signUp" value="Зарегистрировать">
            <input type="button" name="signIn" onclick="location.href='/signIn'" value="Войти">
        </label><br>
    </form>
</body>
</html>
