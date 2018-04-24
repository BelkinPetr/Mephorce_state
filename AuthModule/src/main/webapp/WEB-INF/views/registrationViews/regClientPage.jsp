<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Кинетик
  Date: 02.01.2017
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация Клиента</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <style type="text/css">
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/cssforpr.css" %>
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/bootstrap.min.css" %>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<%--
    Все сообщения ${<название сообщения} буду ошибками в случае неправильного заполнения/ не заполнения поля, около которого появляется
--%>

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
        <spring:form method="post"  modelAttribute="user" action="regClientAction">
            <h1>Регистрация Заказчика</h1>
            <spring:input path="thirdName" type="text" placeholder="Фамилия"/>${famMessage}   <br/>
            <spring:input path="firstName" type="text" placeholder="Имя"/>${nameMessage}   <br/>
            <spring:input path="secName" type="text" placeholder="Отчество"/>${secNameMessage}   <br/>
            <spring:input path="password" type="password" placeholder="Пароль"/>${passMessage} <br/>
            <spring:input path="confPassword" type="password" placeholder="Повторите пароль"/> ${confPassMessage}<br/>
            ${errMessage}<br/>
            <spring:button>Зарегистрироваться</spring:button>
        </spring:form>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <span>&copy;2017 Mephorce</span>
    </div>
</footer>

</body>
</html>
