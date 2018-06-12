<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: kinetik
  Date: 05.03.17
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title> Персональная страница Заказчика </title>
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
    <div class="col-md-3" id="left-menu-bar">
       <div class="user-info-and-menu">
            <div class="btn-group btn-group-vertical">
                <form>
                    <div class="sidebar-header alert alert-info ">Меню</div>
                  <!--  <li class=""> <a href="/PPersCabinClient">Мои проекты</a></li>-->
                    <li class=""><a href="#"><span class="glyphicon glyphicon-comment"> Чат с модератором </span> </a> </li>
                    <li class=""><a href="/goToProjectAppender"><span class="glyphicon glyphicon-plus"> Новый проект </span></a> </li>
                    <!--<li class=""><a href="/PCabinClient">Профиль</a> </li>-->
                    <li class=""><a href="/community/?role=client"><span class="glyphicon glyphicon-question-sign"> Сообщество </span> </a> </li>
                   <!-- <li class=""><a href="/">Выход</a> </li>-->
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-9">
        <h3 id="lk-zakaz" align="center">Личный кабинет Заказчика</h3>
     <!--   <div class="container">
            <div class="personal-page-block">
                <h1>
                    ${Client.clFamily}
                    ${Client.clFirstName}
                    ${Client.clSecName}
                </h1>
                <div class="col-md-6">
                    <div style="background-color: #636c72; width: 180px; height: 180px;">
                        <span class=""
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="personal-info">
                        <b>Контактная информация:</b>
                        <br>
                        <c:if test="${not empty Client.clPhone}">
                            Телефон:&nbsp;&nbsp;${Client.clPhone} <br>
                        </c:if>
                        <c:if test="${not empty Client.clEmail}">
                            E-mail:&nbsp;&nbsp;${Client.clEmail} <br><br>
                        </c:if>
                        <b>Информация о себе:</b>
                        <br>
                        <c:if test="${not empty Client.clHometown}">
                            Город:&nbsp;&nbsp;${Client.clHometown} <br>
                        </c:if>
                        Статус:&nbsp;&nbsp;Заказчик
                        <br><br>
                        <a href="/PClientChangeInfo">Изменить</a>
                        <br>
                    </div>
                </div>
            </div>
        </div> -->
        <div class="container">
            <div class="client-person-project">
                <h1>Мои проекты</h1>
                <div class="project-table">
                    <div class="table-responsive">
                        <table class="table table-striped tablesorter"  id="myTable" >
                            <thead>
                            <tr>
                                <th class="text-center">#</th>
                                <th class="text-center">Название</th>
                                <th class="text-center">Описание</th>
                                <th class="text-center">Дата создания</th>
                                <th class="text-center">Статус</th>
                                <th class="text-center">Дата готовности</th>
                   <!--             <th class="text-center">Сумма</th>
                                <th class="text-center">Модератор</th>-->
                            </thead>
                            <tbody id="prodReportTable" >
                            <c:forEach var="listValue" items="${projectList}">
                                <tr >
                                    <td class="text-center"><a href="/goToProjectInfo?prId=${listValue.prId}">${listValue.prId}</a></td>
                                    <td class="text-center"><a href="/goToProjectInfo?prId=${listValue.prId}">${listValue.title}</a></td>
                                    <td class="text-center"><div class="table-responsive cell" >${listValue.description}</div></td>
                                    <td class="text-center"><c:set var = "date" value="${listValue.dateOfCreation}" />
                                        <fmt:formatDate type = "date" value = "${date}"/></td>
                                    <td class="text-center"><c:set var = "date2" value="${listValue.dateOfReady}" />
                                        <fmt:formatDate type = "date" value = "${date2}"/></td>
                                    <td class="text-center">${listValue.status}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
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
