<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Person page</title>
    <meta charset="UTF-8">

</head>

<body style="background-color: antiquewhite">
<img src="images/abracadabra.gif" alt="My team picture"/>
<h1 style="color: blue" align="center">MyTeam</h1>
<h3 style="color: darkslategray" align="center">Уважаемый ${person.name} !</h3>
<h3 style="color: darkslategray" align="center">Добро пожаловать в MyTeam</h3>

<form:form modelAttribute="person">

    <div>Имя: ${person.name}  </div>
    <br>
    <div> Фамилия: ${person.surname}</div>
    <br>
    <div> Ваш логин: ${person.login}</div>
    <br><br>
</form:form>

<form:form action="/team/addinfo" modelAttribute="extrainfo" method="post">
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

    <td>
        <input type="submit" value="Add">
    </td>
    </form:form>


    <td><input type="button" value="Show My team" onclick="window.location.href='/team/persons'"
               formmethod="get"></td>
    <br><br>
    <a href="/">EXIT</a>
</body>
</html>
