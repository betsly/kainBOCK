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
<fmt:formatDate type="time" value="${now}"/><br/>
<html>
<link href="timelineDesign.css" rel="stylesheet">
<script src="event.js" type="text/javascript"></script>
<head>
    <title>Title</title>
</head>
<form action="./Controller" method="POST">
    <body>

    <div class="container">
        <div class="leftbox">
            <span id="timeVertikal">Timeline</span>
        </div>
        <div class="rightbox">
            <div class="rb-container">
                <ul class="rb">
                    <c:choose>
                        <c:when test="${events == null || events.size() == 0}">
                            <h2>Momentan sind keine Events vorhanden.</h2>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${events}" var="event">
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
        <button type="submit" name="addTimeline" value="addTimeline" class="btn">Add to Timeline</button>
        <button type="button" class="btn" onclick="closeFormLogin()">Cancel</button>
    </div>

    </body>
</form>
</html>
