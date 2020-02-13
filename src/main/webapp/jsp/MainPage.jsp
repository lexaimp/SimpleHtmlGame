<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SimpleHtmlGame</title>
    <link rel="stylesheet" href="css/reset.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body class="mainLayout">
<h1>Главная страница</h1>
<div>
    <form class="background" action="Duels" method="get">
        <button type="submit" name="action" value="start">Дуэль</button>
        <button type="submit" name="action" value="exit">Выход</button>
    </form>
</div>
</body>
</html>