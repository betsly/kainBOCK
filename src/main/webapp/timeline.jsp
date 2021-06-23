<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jiazh
  Date: 08.06.2021
  Time: 09:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<html>
<link href="timelineDesign.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">-
<script src="event.js" type="text/javascript"></script>
<head>
    <title>Title</title>
</head>

<body>
<form action="./Controller" method="POST">
        <div class="container">
            <div class="leftbox">
                <span id="timeVertikal">Timeline</span>
            </div>
            <div class="rightbox">
                <div class="rb-container">
                    <ul class="rb">
                        <c:choose>
                            <c:when test="${events == null || events.size() == 0}">
                                <h2 style=" color: white">Momentan sind keine Events vorhanden.</h2>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${events}" var="event">
                                    <form action="./Controller" method="POST">
                                        <button type="submit" class="btDelete" name="btDelete" value="${event.id}"><i class="fa fa-trash" aria-hidden="true"></i></button>
                                    </form>
                                    <li class="rb-item" ng-repeat="itembx">
                                        <div class="timestamp">
                                                ${event.getFormatDate()}<br>
                                                ${event.getFormatTime()}
                                        </div>
                                        <div class="item-title">${event.description}</div>
                                    </li>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>


                    </ul>
                    <div class="" style="margin-top:5%">
                        <button name="btEvent" type="submit" class="icon-btn add-btn" onclick="openFormLogin()">
                            <div class="add-icon"></div>
                            <div class="btn-txt">Add</div>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-popup" id="eventForm">
            <a class="eventTitle">Add an event</a>
            <label for="description"><span class="descriptionLabel">Description</span></label>
            <textarea id="description" placeholder="My birthday" name="description" class="description" required></textarea>
            <button type="submit" name="addTimeline" class="btn">Add to Timeline</button>
            <button type="button" class="btn" onclick="closeFormLogin()">Cancel</button>
        </div>
</form>
<form action="./Controller" method="POST">
    <button type="submit" class="btHomes" name="btHome"><i class="fa fa-home w3-xxlarge"></i></button>
</form>
</body>
</html>
