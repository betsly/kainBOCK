<%--
  Created by IntelliJ IDEA.
  User: zmugg
  Date: 05.05.2021
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>KainBOCK-Homepage</title>
        <link href="homepage.css" rel="stylesheet">
        <script src="kainBOCKJavaScript.js" type="text/javascript"></script>
    </head>
    <body style=" background-color: #000000">
        <form action="./Controller" method="post">

            <div class="sign">
                <span class="fast-flicker">K</span>ain<span class="flicker">BO</span>CK
            </div>






            <div class="btDiv">
                <div class="buttons"><input class="bt" type="submit" name="bmiButton" value="BMI berechnen"></div>
                <div class="buttons"><input class="bt" type="submit" name="videoButton" value="Trainings-Videos"></div>
                <div class="buttons"><input class="bt" type="submit" name="changeProfile" value="Profil ändern"></div>
                <div class="buttons"><input class="bt" type="submit" name="logout" value="Logout"></div>
            </div>



        </form>
    </body>
</html>
