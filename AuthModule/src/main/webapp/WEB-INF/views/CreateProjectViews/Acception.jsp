<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 26.03.2017
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Утверждение модераторов</title>
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
        <%@include file="/WEB-INF/js/jquery.tablesorter.min.js" %>
    </script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/PCabinClient">MePhorce</a>
        <nav class="nav nav-masthead">
            <a class="nav-link" href="/PPersCabinClient"><span class="glyphicon glyphicon-user"> Профиль </span> </a>
            <a class="nav-link" href="/aboutUs"><span class="glyphicon glyphicon-info-sign"> О проекте</span> </a>
            <!--  <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Создать аккаунт</a>
                  <ul class="dropdown-menu inverse">
                      <li><a href="regStudent">Исполнитель</a></li>
                      <li><a href="regAdmin">Администратор</a></li>
                      <li><a href="regClient">Заказчик</a></li>
                  </ul>
              </li>-->
            <a class="nav-link" href="/contacts"><span class="glyphicon glyphicon-comment"> Контакты</span></a>
            <a class="nav-link active" href="/"><span id="replace" class="glyphicon glyphicon-log-out">  </span></a>
        </nav>
    </div>
</nav>

<div class="row">
    <div class="col-md-3">
        <div class="user-info-and-menu">
            <div class="navbar-header header">
                <h1>
                    ${Client.clFamily}
                    ${Client.clFirstName}
                    ${Client.clSecName}
                </h1>
                <hr>
            </div>
            <div class="btn-group btn-group-vertical">
                <form>
                    <div class="sidebar-header alert alert-info ">Меню</div>
                    <!--  <li class=""> <a href="/PPersCabinClient">Мои проекты</a></li>-->
                    <li class=""><a href="#"><span class="glyphicon glyphicon-comment"> Чат с модератором </span> </a> </li>
                    <li class=""><a href="/goToProjectAppender"><span class="glyphicon glyphicon-plus"> Новый проект </span></a> </li>
                    <!--<li class=""><a href="/PCabinClient">Профиль</a> </li>-->
                    <li class=""><a href="/community/?role=client"><span class="glyphicon glyphicon-question-sign"> Сообщество </span> </a> </li>
                    <li class=""><a href="/PClientInvitation?prId=${project.prId}"><span class="glyphicon glyphicon-share"> Новое приглашение</span></a></li>
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-9">
        <h3>Мои приглашения в проекты</h3>
        <div class="table-responsive">
            <table class="table table-striped tablesorter"  id="myTable" >
                <thead>
                <tr>
                    <th class="text-center">Ответившие</th>
                    <th class="text-center">Решение</th>
                    <!--   <th class="text-center">Дата создания</th>
                        <th class="text-center">Статус</th>
                        <th class="text-center">Дата готовности</th>
                        <th class="text-center">Сумма</th>-->
                    <th class="text-center"></th>
                    <th class="text-center"></th>
                </tr>
                </thead>
                <tbody id="prodReportTable" >
                <c:forEach var="listValue" items="${accept}">
                    <tr >
                        <td class="text-center">${listValue.modFamily} ${listValue.modFirstName} ${listValue.modSecName}</td>
                        <td class="text-center">Принято</td>
                        <!--<td class="text-center">{listValue.dateOfCreation}</td>
                        <td class="text-center">{listValue.status}</td>
                        <td class="text-center">{listValue.dateOfReady}</td>
                        <td class="text-center">{listValue.sum}</td>-->
                        <td class="text-center"><a href="/SubmitModerator?prId=${project.prId}&modId=${listValue.modId}">Принять</a></td>
                        <td class="text-center"><a href="/denyModerator?prId=${project.prId}">Отклонить</a></td>
                    </tr>
                </c:forEach>
<c:forEach var="listValue" items="${projectList}">
    <tr >
    <td class="text-center">${listValue.modFamily} ${listValue.modFirstName} ${listValue.modSecName}</td>
    <td class="text-center">Октлонено</td>
    <!--<td class="text-center">{listValue.dateOfCreation}</td>
    <td class="text-center">{listValue.status}</td>
    <td class="text-center">{listValue.dateOfReady}</td>
    <td class="text-center">{listValue.sum}</td>-->
    <td class="text-center">Добавление невозможно</td>
        <td class="text-center"><a href="/addModeratorToBlackList?modId=${listValue.modId}&prId=${project.prId}">Убрать из списка</a></td>
    </tr>
</c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</div>
<footer class="footer">
    <div class="container">
        <p class="text-muted">&copy;2017 Mephorce</p>
    </div>
</footer>
</body>
</html>

