<%--
  Created by IntelliJ IDEA.
  User: jiazh
  Date: 08.06.2021
  Time: 09:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<link href="timelineDesign.css" rel="stylesheet">
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
                <li class="rb-item" ng-repeat="itembx">
                    <div class="timestamp">
                        3rd May 2020<br> 7:00 PM
                    </div>
                    <div class="item-title">Chris Serrano posted a photo on your wall.</div>

                </li>
                <li class="rb-item" ng-repeat="itembx">
                    <div class="timestamp">
                        19th May 2020<br> 3:00 PM
                    </div>
                    <div class="item-title">Mia Redwood commented on your last post.</div>

                </li>

                <li class="rb-item" ng-repeat="itembx">
                    <div class="timestamp">
                        17st June 2020<br> 7:00 PM
                    </div>
                    <div class="item-title">Lucas McAlister just send you a message.</div>

                </li>
            </ul>
            <div class="" style="margin-top:5%">
                <button name="btEvent" type="submit" class="icon-btn add-btn">
                    <div class="add-icon"></div>
                    <div class="btn-txt">Add</div>
                </button>
            </div>
        </div>
    </div>
</div>
<div class="form-popup" id="loginForm">
    <form action="./Controller" class="form-container" method="post">
        <h1>Login</h1>
        <label><b>Date</b></label>
        <input type="text" placeholder="Enter Email" name="date" required>
        <label><b>Description</b></label>
        <input type="text" placeholder="My birthday" name="description" required>
        <button type="submit" name="addTimeline" value="addTimeline" class="btn">Add to Timeline</button>
        <button type="button" class="btn cancel" onclick="closeFormLogin()">cancel</button>
    </form>
</div>

</body>
</form>
</html>
