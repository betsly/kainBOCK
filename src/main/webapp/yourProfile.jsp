<%--
  Created by IntelliJ IDEA.
  User: zmugg
  Date: 29.06.2021
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>yourProfile</title>
    <link href="yourProfile.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
</head>
<body style="background-color: black">
<div class="header">
    <p>Profil anzeigen</p>
    <form action="./Controller" method="post">
        <button class="btHome" name="btHome" type="submit"><i class="fa fa-home w3-xxlarge"></i></button>
    </form>
</div>
<div class="icon">
    <div style="font-size: 300px">
        <i class="fa fa-user fa-10x" aria-hidden="true"></i></div>
</div>
<div class="profile">
    <div class="profileItems">
        <p><strong>Name:</strong> ${user.name}</p>
        <p><strong>E-Mail:</strong> ${user.email}</p>
        <p><strong>Geburtsdatum:</strong> ${user.date()}</p>
        <p><strong>Geschlecht:</strong> ${user.gender}</p>
        <p><strong>Dein Ziel:</strong> ${user.goal}</p>
    </div>

</div>



</body>
</html>
