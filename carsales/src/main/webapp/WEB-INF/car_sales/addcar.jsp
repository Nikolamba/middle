<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
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

<c:if test="${not empty error}">
    <div style="background-color: red">
        <div class="error" style="background-color: red">${error}</div>
        <script>disabledButton()</script>
    </div>
</c:if>
<c:remove var="error" scope="request"/>

<sf:form method="post" modelAttribute="car" action="/carsales/app/addcar" enctype="multipart/form-data" name="form">
    <fieldset>
        <table id="add_car_table" class="table table-bordered">
            <tr>
                <th>Brand</th>
                <td>
                    <select id="brands" name="brands" title="Select car's brand">
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
                    <sf:select path="model" title="Select car's model" disabled="true" id="models">
                        <option value="Select model" disabled>Select model</option>
                    </sf:select>
                    <sf:errors path="model" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <th>Body type</th>
                <td>
                    <sf:select path="bodyType" title="Select car's body type" id="body_type">
                        <sf:option value="Select body type" disabled="true"/>
                        <sf:options items="${types}" />
                    </sf:select>
                    <sf:errors path="bodyType" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <th>Color</th>
                <td>
                    <sf:input path="color" id="color" title="Enter car's color"/>
                    <sf:errors path="color" cssClass="error"/>
                </td>
            </tr>

            <tr>
                <th>Year of issue</th>
                <td>
                    <sf:input path="year" id="year" title="Enter car's year of issue"/>
                    <sf:errors path="year" cssClass="error"/>
                </td>
            </tr>
        </table>

    </fieldset>


    Select picture to upload:
    <input type="file" name="image" />
    <br/><br/>

    <input id="input_button" type="submit" value="Add new car" disabled>
</sf:form>

<h2>Welcome : ${pageContext.request.userPrincipal.name} |
    <a href="<c:url value="/app/logout" />" > Logout</a>
</h2>

</body>
