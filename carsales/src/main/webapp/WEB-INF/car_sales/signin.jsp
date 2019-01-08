<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
        <style>
            <%@include file="cars_style.css"%>
        </style>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <title>Login</title>

</head>
<body>
<h1>Autorization</h1>
<c:if test="${not empty error}">
    <div class="error" style="background-color: red">${error}</div>
</c:if>
<c:remove var="error" scope="request"/>

<form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
<%--<form action="${pageContext.servletContext.contextPath}/app/signin" method="post">--%>
    <table>
        <tr><td>Name: <input type="text" name = "name"></td></tr>
        <tr><td>Password: <input type = "text" name="pass"/></td></tr>
        <tr><td><button class="btn btn-primary" type="submit">Login</button></td></tr>
    </table>
</form>
</body>
</html>
