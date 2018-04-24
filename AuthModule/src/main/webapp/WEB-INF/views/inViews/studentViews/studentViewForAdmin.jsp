<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: ilysko
  Date: 28.06.17
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Personal Student Page </title>
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
        <a class="navbar-brand" href="/">MePhorce</a>
        <nav class="nav nav-masthead">
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

<div class="row">
    <div class="col-md-2" id="left-menu-bar">
        <div class="user-info-and-menu">
            <div class="btn-group btn-group-vertical">
                <form>
                    <div class="sidebar-header alert alert-info ">Меню</div>
                    <li class=""> <a href="/PPersCabinAdmin">Мои проекты</a></li>
                    <li class=""><a href="#">Чат с клиентом</a> </li>
                    <li class=""><a href="#">Мои приглашения в проект</a> </li>
                    <li class=""><a href="/PCabinAdmin">Профиль</a> </li>
                    <li class=""><a href="/community/?role=admin">Сообщество</a> </li>
                    <li class=""><a href="/">Выход</a> </li>
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-10">
        <div class="container">
            <div class="personal-page-block">
                <h1>
                    ${Student.stFamily}
                    ${Student.stFirstName}
                    ${Student.stSecName}
                </h1>
                <div class="col-md-6">
                    <div style="background-color: #636c72; width: 180px; height: 180px;">
                        <img src="../../../../resources/prof_pic.jpg" alt="">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="personal-info">
                        <b>Контактная информация:</b>
                        <br>
                        <c:if test="${not empty Student.stPhone}">
                            Телефон:&nbsp;&nbsp;${Student.stPhone} <br>
                        </c:if>
                        <c:if test="${not empty Student.stEmail}">
                            E-mail:&nbsp;&nbsp;${Student.stEmail} <br><br>
                        </c:if>
                        <b>Информация о себе:</b>
                        <br>
                        <c:if test="${not empty Student.stHometown}">
                            Город:&nbsp;&nbsp;${Student.stHometown} <br>
                        </c:if>
                        Статус:&nbsp;&nbsp; Исполнитель
                        <br>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="personal-page-block-skills">
                <h1> Навыки </h1>
                <div class="project-table">
                    <div class="table-responsive-skills">
                        <table class="table table-striped tablesorter"  id="myTable" >
                            <thead>
                            <tr>
                                <th class="text-center">Категория</th>
                                <th class="text-center">Формальный рейтинг</th>
                                <th class="text-center">Неформальный рейтинг</th>
                            </thead>
                            <tbody id="prodReportTable" >
                            <c:forEach items="${Skills}" var="entry">
                                <tr >
                                    <td class="text-center">${entry.key}</td>
                                    <td class="text-center">${entry.value.value}</td>
                                    <td class="text-center">${entry.value.key}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="personal-page-block-skills">
                <h1> Проекты участника </h1>
                <div class="project-table">
                    <form class="form-inline">
                        <h4>Проектов</h4>
                        <div class="shadow">
                            <span class="badge badge-info">${fn:length(projectList)}</span>
                        </div>
                    </form>
                    <h5>Мои проекты</h5>
                    <div class="table-responsive">
                        <table class="table table-striped tablesorter" id="myTable1" >
                            <thead>
                            <tr>
                                <th class="text-center">№</th>
                                <th class="text-center">Название</th>
                                <th class="text-center">Описание</th>
                                <th class="text-center">Статус</th>

                            </thead>
                            <tbody>
                            <c:forEach var="listValue" items="${projectList}" varStatus="counter">
                                <tr>
                                    <td class="text-center"><a href="#">${counter.count}</a></td>
                                    <td class="text-center"><a href="#">${listValue.title}</a></td>
                                    <td class="text-center"><div class="table-responsive cell" >${listValue.description}</div></td>
                                    <c:if test="${listValue.status == 'Есть просроченные задачи'}">
                                        <td class="text-center danger">${listValue.status}</td>
                                    </c:if>
                                    <c:if test="${listValue.status != 'Есть просроченные задачи'}">
                                        <td class="text-center">${listValue.status}</td>
                                    </c:if>
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