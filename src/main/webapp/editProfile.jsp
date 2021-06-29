<%--
  Created by IntelliJ IDEA.
  User: zmugg
  Date: 29.06.2021
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EditProfile</title>
    <link href="editProfile.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
</head>
<body style="background-color: black">
<form action="./Controller" method="post">
    <div class="header">
        <p>Profil ändern</p>
        <button class="btHome" name="btHome" type="submit"><i class="fa fa-home w3-xxlarge"></i></button>
    </div>
    <div class="changePasswort">
        <div class="innerdiv">

            <div><input class="input" type="password" value="" placeholder="altes Password" name="oldPassword"></div>

            <div><input class="input" type="password" value="" placeholder="neues Password" name="newPassword"></div>

            <div><input class="input" type="password" value="" placeholder="neues Password" name="newPassword"></div>
            <div><input class="bt" type="submit" name="changePassword" value="Passwort ändern"></div>
            <label>${errorChangePW}</label>
        </div>
    </div>
    <div class="changeGoal">
        <select name="goal" id="goals">
            <c:forEach var="goal" items="${goals}">
                <option value="${goal.goalID}">${goal.name}</option>
            </c:forEach>
        </select>
        <div><input class="bt" type="submit" name="changeGoal" value="Ziel ändern"></div>

    </div>
</form>
</body>
</html>
