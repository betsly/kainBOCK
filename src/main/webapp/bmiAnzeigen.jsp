<%--
  Created by IntelliJ IDEA.
  User: zmugg
  Date: 01.06.2021
  Time: 09:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>BMI anzeigen</title>
    <link href="bmiAnzeigen.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
</head>
<body style="background-color: black" id="body">


<form action="./Controller" method="post" id="bmiForm">
    <div class="header">
        <p>Dein BMI</p>
        <button class="btHome" name="btHome" type="submit"><i class="fa fa-home w3-xxlarge"></i></button>
    </div>
<div class="bmidiv">

    <div class="deineWerte">
        <span class="bmiWert"><strong>Dein BMI Wert: </strong>${bmiValue}</span>
        <span class="bmiWert"><strong>Dein Alter: </strong>${ageOfUser} Jahre</span>
    </div>

    <c:choose>


        <c:when test="${ageOfUser >= 18 && ageOfUser <= 24}">
            <div class="tabllenDiv">
                <span class="age">18-24 Jahre</span>
                <table id="tabele">
                    <tr>
                        <td class="gender">W</td>
                        <td class="${bmiValue <19 ? "selected" : "wert"}" style="background-color: #ba7c9f" ><19</td>
                        <td class="${bmiValue >=19 && bmiValue <=24 ? "selected" : "wert"}" style="background-color: #ff65bd" >19-24</td>
                        <td class="${bmiValue >=25 && bmiValue <=28 ? "selected" : "wert"}" style="background-color: #e54f7e">25-28</td>
                        <td class="${bmiValue >=28 ? "selected" : "wert"}" style="background-color: #AB2752">>28</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td class="text">Untergewicht</td>
                        <td class="text">Normalgewicht</td>
                        <td class="text">Übergewicht</td>
                        <td class="text">Adipositas</td>
                    </tr>
                    <tr>
                        <td class="gender">M</td>
                        <td class="${bmiValue < 20 ? "selected" : "wert"}" style="background-color: #ba7c9f"><20</td>
                        <td class="${bmiValue >=20 && bmiValue <=25 ? "selected" : "wert"}" style="background-color: #ff65bd">20-25</td>
                        <td class="${bmiValue >=26 && bmiValue <=29 ? "selected" : "wert"}" style="background-color: #e54f7e">26-29</td>
                        <td class="${bmiValue >=29 ? "selected" : "wert"}" style="background-color: #AB2752">>29</td>
                    </tr>
                </table>
            </div>
        </c:when>
        <c:when test="${ageOfUser >= 25 && ageOfUser <= 34}">
            <span class="age">25-34</span>
            <table class="tabele">
                <tr>
                    <td class="wert" style="background-color: #ba7c9f"><19</td>
                    <td class="wert" style="background-color: #ff65bd">19-24</td>
                    <td class="wert" style="background-color: #e54f7e">25-28</td>
                    <td class="wert" style="background-color: #AB2752">>28</td>
                </tr>
                <tr>
                    <td class="text">Untergewicht</td>
                    <td class="text">Normalgewicht</td>
                    <td class="text">Übergewicht</td>
                    <td class="text">Adipositas</td>
                </tr>
                <tr>
                    <td class="wert" style="background-color: #ba7c9f"><20</td>
                    <td class="wert" style="background-color: #ff65bd">20-25</td>
                    <td class="wert" style="background-color: #e54f7e">26-29</td>
                    <td class="wert" style="background-color: #AB2752">>29</td>
                </tr>
            </table>
        </c:when>
        <c:when test="${ageOfUser >= 35 && ageOfUser <= 45}">
            <span class="age">35-44</span>
            <table class="tabele">
                <tr>
                    <td class="wert" style="background-color: #ba7c9f"><19</td>
                    <td class="wert" style="background-color: #ff65bd">19-24</td>
                    <td class="wert" style="background-color: #e54f7e">25-28</td>
                    <td class="wert" style="background-color: #AB2752">>28</td>
                </tr>
                <tr>
                    <td class="text">Untergewicht</td>
                    <td class="text">Normalgewicht</td>
                    <td class="text">Übergewicht</td>
                    <td class="text">Adipositas</td>
                </tr>
                <tr>
                    <td class="wert" style="background-color: #ba7c9f"><20</td>
                    <td class="wert" style="background-color: #ff65bd">20-25</td>
                    <td class="wert" style="background-color: #e54f7e">26-29</td>
                    <td class="wert" style="background-color: #AB2752">>29</td>
                </tr>
            </table>
        </c:when>
        <c:when test="${ageOfUser >= 45 && ageOfUser <= 54}">
            <span class="age">45-54</span>
            <table class="tabele">
                <tr>
                    <td class="wert" style="background-color: #ba7c9f"><19</td>
                    <td class="wert" style="background-color: #ff65bd">19-24</td>
                    <td class="wert" style="background-color: #e54f7e">25-28</td>
                    <td class="wert" style="background-color: #AB2752">>28</td>
                </tr>
                <tr>
                    <td class="text">Untergewicht</td>
                    <td class="text">Normalgewicht</td>
                    <td class="text">Übergewicht</td>
                    <td class="text">Adipositas</td>
                </tr>
                <tr>
                    <td class="wert" style="background-color: #ba7c9f"><20</td>
                    <td class="wert" style="background-color: #ff65bd">20-25</td>
                    <td class="wert" style="background-color: #e54f7e">26-29</td>
                    <td class="wert" style="background-color: #AB2752">>29</td>
                </tr>
            </table>
        </c:when>
        <c:when test="${ageOfUser >= 55 && ageOfUser <= 64}">
            <span class="age">55-64</span>
            <table class="tabele">
                <tr>
                    <td class="wert" style="background-color: #ba7c9f"><19</td>
                    <td class="wert" style="background-color: #ff65bd">19-24</td>
                    <td class="wert" style="background-color: #e54f7e">25-28</td>
                    <td class="wert" style="background-color: #AB2752">>28</td>
                </tr>
                <tr>
                    <td class="text">Untergewicht</td>
                    <td class="text">Normalgewicht</td>
                    <td class="text">Übergewicht</td>
                    <td class="text">Adipositas</td>
                </tr>
                <tr>
                    <td class="wert" style="background-color: #ba7c9f"><20</td>
                    <td class="wert" style="background-color: #ff65bd">20-25</td>
                    <td class="wert" style="background-color: #e54f7e">26-29</td>
                    <td class="wert" style="background-color: #AB2752">>29</td>
                </tr>
            </table>
        </c:when>
        <c:when test="${ageOfUser >= 55 && ageOfUser <= 64}">
            <span class="age"><65</span>
            <table class="tabele">
                <tr>
                    <td class="wert" style="background-color: #ba7c9f"><19</td>
                    <td class="wert" style="background-color: #ff65bd">19-24</td>
                    <td class="wert" style="background-color: #e54f7e">25-28</td>
                    <td class="wert" style="background-color: #AB2752">>28</td>
                </tr>
                <tr>
                    <td class="text">Untergewicht</td>
                    <td class="text">Normalgewicht</td>
                    <td class="text">Übergewicht</td>
                    <td class="text">Adipositas</td>
                </tr>
                <tr>
                    <td class="wert" style="background-color: #ba7c9f"><20</td>
                    <td class="wert" style="background-color: #ff65bd">20-25</td>
                    <td class="wert" style="background-color: #e54f7e">26-29</td>
                    <td class="wert" style="background-color: #AB2752">>29</td>
                </tr>
            </table>
        </c:when>
    </c:choose>
</div>

</form>

</body>
</html>
