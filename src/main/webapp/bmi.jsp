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
</head>
<body>
<div class="outerdiv">
    <p><b>BMI</b> berechnen</p>
    <label><b>Geschlecht</b></label>
    <div id="gender">
    <input type="radio" id="male" name="gender" value="male">
    <label>M</label>
    <input type="radio" id="female" name="gender" value="female">
    <label>W</label>
    </div>
    <label><b>Alter</b></label>
    <input type="text" placeholder="10.04.2000" name="age" required>
    <label><b>Größe in cm</b></label>
    <input type="text" placeholder="172" name="height" required>
    <label><b>Gewicht in kg</b></label>
    <input type="text" placeholder="62 kg" name="weight" required>
    <button type="submit" name="confirmBMI" value="confirmBMI" class="btn">Berechnen</button>
    <button type="submit" name="exitBMI">Close</button>

</div>
</body>
</html>
