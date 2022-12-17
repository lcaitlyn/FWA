<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
<h1>Регистрация</h1>
  <form enctype="multipart/form-data" method="post">
    <label>Электронная почта: <input name="email" type="email" value="${email}"></label><br>
    <label>Пароль: <input name="password" type="password" value="${firstname}"></label><br>
    <label>
      <input type="submit" name="signIn" value="Войти">
      <input type="button" name="signUp" onclick="location.href='/signUp'" value="Регистрация">
    </label><br>
  </form>
</body>
</html>
