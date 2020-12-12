<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Person page</title>
    <meta charset="UTF-8">
</head>

<body style="background-color: antiquewhite">
<h1 style="color: blue">MyTeam</h1><br>
<h3 style="color: darkslategray">Уважаемый ${person.name} !</h3>
<h3 style="color: darkslategray">Добро пожаловать в MyTeam</h3>

<form:form modelAttribute="person">

    Имя: ${person.name}
    <br><br>
    Ваш логин: ${person.login}<br><br>
</form:form>

<a href="/">EXIT</a>
</body>
</html>
