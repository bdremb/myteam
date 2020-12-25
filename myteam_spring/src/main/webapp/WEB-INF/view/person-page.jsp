<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Person page</title>
    <meta charset="UTF-8">

</head>

<body style="background-color: antiquewhite">
<%--<img src="images/abracadabra.gif" alt="My team picture"/>--%>
<h1 style="color: blue" align="center">MyTeam</h1>
<h3 style="color: darkslategray" align="center">Уважаемый ${person.name} !</h3>
<h3 style="color: darkslategray" align="center">Добро пожаловать в MyTeam</h3>

<form:form action="/team/addinfo" modelAttribute="person">


<td><form:hidden path="id"/></td>

<div>Имя: ${person.name}  </div>
<div> Фамилия: ${person.surname}</div>
<div> Ваш логин: ${person.login}</div>
<div> Возраст: ${person.extraInfo.age}</div>
<div> Город: ${person.extraInfo.city}</div>
<div> e-mail: ${person.extraInfo.email}</div>
<div> телефон : ${person.extraInfo.phoneNumber}</div>
<div> skype: ${person.extraInfo.skype}</div>

<form:form modelAttribute="extrainfo" method="post">

<table>


    <tr>
        <th align="left">Age:</th>
        <td><form:input path="age"/></td>
        <th align="left"><form:errors cssStyle="color: orangered" path="age"/></th>
    </tr>

    <tr>
        <th align="left">Email:</th>
        <td><form:input path="email"/></td>
        <th align="left"><form:errors cssStyle="color: orangered" path="email"/></th>
    </tr>

    <tr>
        <th align="left">City:</th>
        <td><form:input path="city"/></td>
        <th align="left"><form:errors cssStyle="color: orangered" path="city"/></th>
    </tr>

    <tr>
        <th align="left">Phone number:</th>
        <td><form:input path="phoneNumber"/></td>
        <th align="left"><form:errors cssStyle="color: orangered" path="phoneNumber"/></th>
    </tr>

    <tr>
        <th align="left">Skype:</th>
        <td><form:input path="skype"/></td>
        <th align="left"><form:errors cssStyle="color: orangered" path="skype"/></th>
    </tr>


    </form:form>
    <td><input type="button" value="Show My team" onclick="window.location.href='/team/persons'"
               formmethod="get"></td>
    <br><br>
    <td>
        <input type="submit" value="UPDATE">
    </td>

    </form:form>


    <td>
        <a href="/">EXIT</a>
    </td>

</body>
</html>
