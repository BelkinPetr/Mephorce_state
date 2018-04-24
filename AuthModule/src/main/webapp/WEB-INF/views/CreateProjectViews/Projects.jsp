<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>Редактирование проекта</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <style type="text/css">
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/cssforpr.css" %>
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/bootstrap.min.css" %>
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/table-style.css" %>
    </style>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script type="text/javascript" >
        <%@include file="/WEB-INF/js/Selection.js" %>
    </script>
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
    <div class="login-block">
        <h1> Редактирование проекта </h1>
         </br>
        </br>
        <form:form  name="form1" action="/addInformProjectNew?prId=${Project.prId}" method="post" modelAttribute="addInfo">
            <form:input path="cltitle" id="here" type="text" value="${Project.title}" placeholder="${Project.title}"/><br/>
            <form:textarea path="cldescription" id="description"  value="${Project.description}" placeholder="${Project.description}" /> <br/>
            <form:input path="cldateOfCreation" type="text" value="${Project.dateOfCreation}" placeholder="${Project.dateOfCreation}"/>  <br/>
            <form:input path="cldateOfReady" type="text" value="${Project.dateOfReady}" placeholder="${Project.dateOfReady}"/>   <br/>
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