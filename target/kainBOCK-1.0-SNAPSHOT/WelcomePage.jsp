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

    <button class="button"name="login" onclick="openForm()">LOGIN </button>


<br>
<button class="button" name="registeration" onclick="openForm()">REGISTRIEREN</button>

<div class="form-popup" id="myForm">
    <form action="/Controller" class="form-container">
        <h1>Login</h1>

        <label><b>Email</b></label>
        <input type="text" placeholder="Enter Email" name="email" required>

        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <button type="submit" class="btn">Login</button>
        <button type="submit" class="btn cancel" onclick="closeForm()">Close</button>
    </form>
</div>


</body>
</html>
