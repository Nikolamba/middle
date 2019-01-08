<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit car</title>
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
<h1>Edit car <c:out value="${pageContext.request.userPrincipal.name}"/></h1>

<c:if test="${requestScope.error != ''}">
    <div style="background-color: red">
        <c:out value="${requestScope.error}"/>
    </div>
</c:if>
<c:remove var="error" scope="request"/>

<table id="user_car_table" class="table table-bordered">
    <c:forEach items="${requestScope.cars}" var="car">
        <tr><td><img src="${car.picturePath}"></td>
            <td>Brand: <input type="text" value="${car.model.brand.name}" disabled/>
                <br>Model: <input type="text" value="${car.model.name}" disabled/>
                <br>Color: <input type="text" value="${car.color}" disabled/>
                <br>Body type: <input type="text" value="${car.bodyType.name}" disabled/>
                <br>Year of issue: <input type="text" value="${car.year}" disabled/>
                <br>Seller: <input type="text" value="${car.seller.name}" disabled/>
                <br>
                <form action="${pageContext.servletContext.contextPath}/app/editcar" method="post">
                    <input type="hidden" name="carId" value="${car.id}">
                    <br>    <c:choose>
                                <c:when test="${car.status}">
                                    <input type="submit" value="Sold">
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" value="Not sold">
                                </c:otherwise>
                            </c:choose>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<button onclick="window.location = 'http://localhost:8080/carsales/app/cars'">Back</button>

</body>
</html>
