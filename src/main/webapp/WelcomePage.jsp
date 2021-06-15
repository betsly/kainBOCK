<%--
  Created by IntelliJ IDEA.
  User: zmugg
  Date: 07.04.2021
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>KainBOCK</title>
    <link href="welcomeCss.css" rel="stylesheet">
    <script src="kainBOCKJavaScript.js" type="text/javascript"></script>
</head>
<body id="body1">
<div id="position">
<p id="ü1">Willkommen bei <b>KainBOCK</b>!</p>
<p id="info">Mit <b>KainBOCK</b> zur Traumfigur!</p>
<p id="infoMehr">erfahre HIER mehr</p>

<button class="button" name="login" onclick="openFormLogin()">LOGIN </button>
<br>
<button class="button" name="registeration" onclick="openFormRegister()">REGISTRIEREN</button>
</div>
<div class="form-popup" id="loginForm">
    <form action="./Controller" class="form-container" method="post">
        <h1>Login</h1>
        <label><b>Email</b></label>
        <input type="text" placeholder="Enter Email" name="emailLogin" required>
        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="pswLogin" required>
        <button type="submit" name="confirmLogin" value="confirmLogin" class="btn">Login</button>
        <button type="button" class="btn cancel" onclick="closeFormLogin()">Close</button>
    </form>
</div>
<div class="form-popup" id="registerForm">
    <form action="./Controller" class="form-container" method="post">
        <h1>Registrieren</h1>
        <label><b>Geschlecht</b></label>
        <div id="gender">
        <input type="radio" id="male" name="gender" value="male" checked>
        <label>M</label>
        <input type="radio" id="female" name="gender" value="female">
        <label>W</label>
        </div>
        <br>
        <label><b>Name</b></label>
        <input type="text" placeholder="Enter Name" name="name" required>
        <label><b>Alter</b></label>
        <input type="text" placeholder="10.04.2000" name="age" required>
        <label><b>Email</b></label>
        <input type="text" placeholder="Enter Email" name="email" required>
        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>
        <label><b></b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>
        <label><b>Ziel</b></label>
        <div class="select-wrapper">
        <select name="goal" id="goals">
            <c:forEach var="goal" items="${goals}">
                <option value="${goal.goalID}">${goal.name}</option>
            </c:forEach>
        </select>
        </div>
        <button type="submit" name="confirmRegistration" value="confirmRegistration" class="btn">Registrieren</button>
        <button type="button" class="btn cancel" onclick="closeFormRegister()">Close</button>
    </form>
</div>
</body>
</html>
