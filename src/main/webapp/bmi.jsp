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
</head>
<body class="body">
<form action="./Controller" method="POST">
<div class="outerdiv">
    <p id="ueberschriftBMI"><b>BMI</b> berechnen</p>
        <a><b>Größe in cm</b></a>
    <input type="text" placeholder="172" name="height" required></br>
        <a><b>Gewicht in kg</b></a>
    <input type="text" placeholder="62 kg" name="weight" required></br>
        <button type="submit" name="calcBMI" value="confirmBMI" class="btn">Berechnen</button>
        <button type="submit" name="exitBMI">Close</button>

</div>
</form>
</body>
</html>
