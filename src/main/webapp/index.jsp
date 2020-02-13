<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SimpleHtmlGame</title>
    <link rel="stylesheet" href="css/reset.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body class="mainLayout">
<h1>Добро пожаловать!</h1>
<div>
    <form class="background" action="MainPage" method="post">
        <div>
            <label for="name">Ваше имя</label>
            <input type="text" id="name" name="name" required="required" placeholder="Введите имя *">
        </div>
        <div>
            <label for="password">Пароль</label>
            <input type="password" id="password" name="password" required="required" placeholder="Пароль *"></div>
        <button type="submit">Вход</button>
    </form>
</div>
</body>
</html>
