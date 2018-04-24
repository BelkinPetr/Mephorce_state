<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kinetik
  Date: 08.03.17
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <style type="text/css">
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/cssforpr.css" %>
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/bootstrap.min.css" %>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <script
            src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.17/angular.min.js"></script>
    <script src="assets/js/controllers.js"></script>
</head>

<body>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">MePhorce</a>
        <nav class="nav nav-masthead">
            <a class="nav-link active" href="/">Войти</a>
            <a class="nav-link" href="/aboutUs">О проекте</a>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Создать аккаунт</a>
                <ul class="dropdown-menu inverse">
                    <li><a href="regStudent">Исполнитель</a></li>
                    <li><a href="regAdmin">Администратор</a></li>
                    <li><a href="regClient">Заказчик</a></li>
                </ul>
            </li>
            <a class="nav-link" href="/contacts">Контакты</a>
        </nav>
    </div>
</nav>

<div class="container">
    <div class="login-block">
        <form:form method="post" modelAttribute="addInf" action="addInformStudent">
            <form:input path="birthdate" type="date" value="${Student.stBirthdate}" placeholder="Дата рождения" id="birthdate"/>${birthMessage}<br/>
            <form:input path="e_mail" type="email" value="${Student.stEmail}" placeholder="E-mail" id="email"/>${emailMessage}<br/>
            <form:input path="phone_number" type="text" value="${Student.stPhone}" placeholder="Номер телефона"/>${phoneMessage}<br/>
            <form:input path="hometown" type="text" value="${Student.stHometown}" placeholder="Город"/>${hometownMessage}<br/>
            <form:button> Далее </form:button>
            ${errorMessage}
        </form:form>
        <a href="/PCabinStudentControl">Заполнить позже</a>
    </div>
</div>

</body>
</html>
