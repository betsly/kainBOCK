<%--
  Created by IntelliJ IDEA.
  User: zmugg
  Date: 07.04.2021
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>KainBOCK</title>
    <link href="welcomeCSS.css" rel="stylesheet">
    <script src="kainBOCKJavaScript.js" type="text/javascript"></script>


</head>
<body id="body1">
<h1 id="ü1">Willkommen bei <strong>KainBOCK</strong></h1>
<p id="info">Mit <strong>KainBOCK</strong> zur Traumfigur!</p>
<p id="infoMehr">erfahre HIER mehr</p>
<button class="button" name="login" onclick="openFormLogin()">LOGIN </button>
<br>
<button class="button" name="registeration" onclick="openFormRegister()">REGISTRIEREN</button>
<div class="form-popup" id="loginForm">
    <form action="/Controller" class="form-container">
        <h1>Login</h1>

        <label><b>Email</b></label>
        <input type="text" placeholder="Enter Email" name="emailLogin" required>

        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="pswLogin" required>

        <button type="submit" class="btn">Login</button>
        <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
    </form>
</div>
<div class="form-popup" id="registerForm">
    <form action="/Controller" class="form-container">
        <h1>Registrieren</h1>

        <label><b>Geschlecht</b></label>
        <div id="gender">
        <input type="radio" id="male" name="gender" value="male">
        <label>M</label>
        <input type="radio" id="female" name="gender" value="female">
        <label>W</label>
        <input type="radio" id="other" name="gender" value="other">
        <label>Other</label>
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
        <select name="goal" id="goals">
            <option value="weightloss">Abnehmen</option>
            <option value="musclegain">Muskelaufbau</option>
        </select>
        <button type="submit" class="btn">Login</button>
        <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
    </form>
</div>




</body>
</html>
