<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SimpleHtmlGame</title>
    <link rel="stylesheet" href="css/reset.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body class="mainLayout">
<h1>Страница дуэлей</h1>
<div>
    <form class="background" action="Duels" method="get">
        <p>Мой рейтинг ${rating}</p>
        <button type="submit">Начать дуэль</button>
        <button type="submit">Выход</button>
    </form>
</div>
</body>
</html>
