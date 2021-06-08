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
<body>

<div class="container">

    <div class="box">
        <div class="container-3">
            <span class="icon"><i class="fa fa-search"></i></span>
            <input type="search" id="search" placeholder="Search..." />
        </div>
    </div>

    <div class="leftbox">
        <nav>
            <a id="dashboard"><i class="fas fa-tachometer-alt"></i></a>
            <a id="profile"> <i class="fas fa-user"></i> </a>
            <a id="settings"> <i class="fas fa-cog"></i> </a>
            <a id="messages"> <i class="fas fa-comments"></i> </a>
            <a id="notification"> <i class="fas fa-bell"></i> </a>
        </nav>
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

        </div>
    </div>
</div>

</body>
</html>
