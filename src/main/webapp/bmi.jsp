<%--
  Created by IntelliJ IDEA.
  User: zmugg
  Date: 03.05.2021
  Time: 07:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dein BMI</title>
    <link href="bmi.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Teko:700&display=swap" rel="stylesheet">
</head>
<body class="body">
<form action="./Controller" method="POST">
    <span id="ueberschriftBMI"><b>BMI</b> berechnen</span>
<div class="outerdiv">
        <a><b>Größe in cm</b></a>
    <input type="text" placeholder="172" name="height" id="cm" required></br>
        <a><b>Gewicht in kg</b></a>
    <input type="text" placeholder="62" name="weight" required></br>
    </div>
    <button type="submit" name="calcBMI" value="confirmBMI" class="btn">BERECHNEN</button></br>
    <button type="submit" name="exitBMI" value="exitBMI" class="btn" onclick="window.history.back()">BACK</button>
    <p id="motiv">NOW or NEVER!</p>


</form>
</body>
</html>
