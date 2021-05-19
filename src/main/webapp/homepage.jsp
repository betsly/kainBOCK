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
    <body>
        <form action="./Controller" method="post">
            <p>Wilkommen zurück ${user}</p>
            <button>☰</button>
            <nav class="menu">
                <ul>
                    <li>
                        <button onclick="alert('Hello there!')">
                            Display Greeting
                        </button>
                    </li>
                    <li>
                        <button onclick="print()">
                            Print This Page
                        </button>
                    </li>
                </ul>
            </nav>

            <p>${bmi}</p><button name="BMI" value="BMI">BMI</button>


        </form>
    </body>
</html>
