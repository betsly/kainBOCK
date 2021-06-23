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
<head>
    <title>Title</title>
    <link href="videos.css" rel="stylesheet">
</head>
<body style="background-color: black">
<span id="title">TRAINING-VIDEOS</span>
<div class="auto">
    <details>
        <summary class="summary">Zusammenfassung Punkt 1</summary>
        <video controls='true' height='200' loop='false' width='300'>
            <source src='https://res.cloudinary.com/dprmpoqzc/video/upload/kainbock/JiazhouVideo_1_rbc4i7.mp4'
                    type='video/mp4'>
        </video>
    </details>
    <details>
        <summary class="summary">Zusammenfassung Punkt 1</summary>
        <video controls='true' height='200' loop='false' width='300'>
            <source src='https://res.cloudinary.com/dprmpoqzc/video/upload/kainbock/JiazhouVideo_1_rbc4i7.mp4'
                    type='video/mp4'>
        </video>
    </details>
    <c:forEach var="video" items="${videos}">
        <details>
            <summary class="summary">${video.name}</summary>
            <video controls='true' height='200' loop='false' width='300'>
                <source src='${video.link}' type='video/mp4'>
            </video>
        </details>
    </c:forEach>


</div>

</body>
</html>
