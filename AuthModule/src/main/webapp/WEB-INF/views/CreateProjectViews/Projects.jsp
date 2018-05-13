<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kinetik
  Date: 06.03.17
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css"/>
    <title>Редактирование проекта</title>
    <style type="text/css">
        <%@include file="/WEB-INF/css/bootstrap-4.0.0-alpha.6-dist/css/cssforpr.css" %>
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/bootstrap.min.css" %>
        <%@include file="/WEB-INF/css/zabuto_calendar.min.css" %>
    </style>
    <script src="../../js/vendor/jquery-3.2.1.min.js">
    </script>
    <script src="../../js/vendor/jquery.maskedinput.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/locale/ru.js"></script>
    <script src="WEB-INF/js/vendor/zabuto_calendar.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">MePhorce</a>
        <nav class="nav nav-masthead">
            <a class="nav-link" href="/">Войти</a>
            <a class="nav-link" href="/aboutUs">О проекте</a>
            <a class="nav-link" href="/registrate">Создать аккаунт</a>
            <a class="nav-link" href="/contacts">Контакты</a>
        </nav>
    </div>
</nav>

<div class="container">
    <div class="create-project-block">
        <h1> Редактирование проекта </h1>
         </br>
        </br>
        <form:form  name="form1" action="/addInformProjectNew?prId=${Project.prId}" method="post" modelAttribute="addInfo">
            <form:input path="cltitle" id="here" type="text" value="${Project.title}" placeholder="${Project.title}"/><br/>
            <form:input path="cldescription" type="text" value="${Project.description}" /> <br/>
            <form:input path="cldateOfCreation" type="date" name="calendar" value="${Project.dateOfCreation}" placeholder="${Project.dateOfCreation}"/>  <br/>
            <form:input path="cldateOfReady" type="date" name="calendar" value="${Project.dateOfReady}" placeholder="${Project.dateOfReady}"/>   <br/>
            <form:input path="clstatus" type="text" value="${Project.status}" placeholder="${Project.status}"/>   <br/>
            <form:input path="clsum" type="text" value="${Project.sum}" placeholder="${Project.sum}"/>   <br/>
            <br/>
            <p><form:button id="button" value=" Изменить ">Изменить</form:button></p>
            <p><a href="/PClientInvitation?prId=${Project.prId}"> Пригласить модераторов в проект  </a></p>
            <p><a href="#"> Прикрепить файл </a></p>
            <p><a href="/addModeratorToProject?prId=${Project.prId}">Утвердить модераторов</a></p>
        </form:form>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <p class="text-muted">&copy;2017 Mephorce</p>
    </div>
</footer>

</body>
</html>