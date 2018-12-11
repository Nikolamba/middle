<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="container">
    <h2>Список пользователей</h2>
    <form action="${pageContext.servletContext.contextPath}/users.do" method="post">
        name : <input type="text" name="name"><br/>
        <input type="submit"><br/>
    </form>
    <table border="1">
        <tr>
            <td>Имя</td>
        </tr>
        <c:forEach items="${users}" var="user" varStatus="status">
        <tr valign="top">
            <td>${user.name}</td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>