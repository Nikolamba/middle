<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>Creating a new advertisement</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        <%@include file="cars_style.css"%>
    </style>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <script>
        <%@include file="add_car_script.js"%>
    </script>
</head>
<body>
<h1>Creating a new advertisement</h1>

<c:if test="${requestScope.error != ''}">
    <div style="background-color: red">
        <c:out value="${requestScope.error}"/>
        <script>disabledButton()</script>
    </div>
</c:if>
<c:remove var="error" scope="request"/>

<form action="${pageContext.servletContext.contextPath}/app/addcar" method="post" enctype="multipart/form-data">
    <table id="add_car_table" class="table table-bordered">
        <tr>
            <th>Brand</th>
            <td>
                <select id="brands" name="brands" title="Select car's brand" required>
                    <option disabled>Select brand</option>
                    <c:forEach items="${requestScope.brands}" var="brand">
                        <option value="${brand.id}"><c:out value="${brand.name}"/></option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>Model</th>
            <td>
                <select id="models" name="models" title="Select car's model" required disabled>
                    <option disabled>Select model</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>Body type</th>
            <td>
                <select id="body_type" name="body_type" title="Select car's body type" required>
                    <option disabled>Select body type</option>
                    <c:forEach items="${requestScope.types}" var="type">
                        <option value="${type.id}"><c:out value="${type.name}"/></option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>Color</th>
            <td>
                <input type = "text" id="color" name = "color" title="Enter car's color"/>
            </td>
        </tr>
        <tr>
            <th>Year of issue</th>
            <td>
                <input type="number" id="year" name = "year" title="Enter car's year of issue"/>
            </td>
        </tr>
    </table>

    Select picture to upload:
    <input type="file" name="uploadFile" />
    <br/><br/>

    <input id="input_button" type="submit" value="Add new car" disabled>
</form>

<c:choose>
    <c:when test="${requestScope.user != null}">
        <form action="${pageContext.servletContext.contextPath}/app/signin" method="get">
            <input type="submit" value="SignOut">
        </form>
    </c:when>
    <c:otherwise>
        <form action="${pageContext.servletContext.contextPath}/app/signin" method="get">
            <input type="submit" value="SignIn">
        </form>
    </c:otherwise>


</c:choose>


</body>
