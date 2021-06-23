<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zmugg
  Date: 15.06.2021
  Time: 09:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<head>
    <title>Title</title>
    <link href="videos.css" rel="stylesheet">
</head>
<body style="background-color: black">
<span id="title">TRAINING-VIDEOS</span>
<div class="auto">
    <c:forEach var="video" items="${videos}">
        <details>
            <summary class="summary">${video.name}</summary>
            <video controls='true' height='400' loop='false' width='800' class="vid">
                <source src='${video.link}' type='video/mp4'>
            </video>
        </details>
    </c:forEach>


</div>
<form action="./Controller" method="POST">
    <button type="submit" class="btHomes" name="btHome"><i class="fa fa-home fa-3x"></i></button>
</form>
</body>
</html>
