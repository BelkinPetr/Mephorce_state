<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title> Personal Admin Page3 </title>
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
    <jsp:useBean id="now" class="java.util.Date"/>
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
            <a class="nav-link active" href="/">Выйти</a>
        </nav>
    </div>
</nav>

<div class="row">
    <div class="col-md-3">
        <div class="user-info-and-menu">
            <div class="navbar-header header">
                <h1>
                    ${Admin.modFamily}
                    ${Admin.modFirstName}
                    ${Admin.modSecName}
                </h1>
                <hr>
            </div>
            <div class="btn-group btn-group-vertical">
                <form>
                    <div class="sidebar-header alert alert-info ">Меню</div>
                    <li class=""> <a href="/PPersCabinAdmin">Мои проекты</a></li>
                    <li class=""><a href="#">Чат с клиентом</a> </li>
                    <li class=""><a href="#">Мои приглашения в проект</a> </li>
                    <li class=""><a href="/PCabinAdmin">Профиль</a> </li>
                    <li class=""><a href="/PCommunityNewsAdmin">Сообщество</a> </li>
                    <li class=""><a href="/">Выход</a> </li>
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-9">
        <div class="project-table">
            <form class="form-inline">
                <h3>Всего проектов</h3>
                <div class="shadow">
                    <span class="badge badge-info">${fn:length(projectList)}</span>
                </div>
            </form>
            <h4>Мои проекты</h4>
            <div class="table-responsive">
                <table class="table table-striped tablesorter"  id="myTable" >
                    <thead>
                    <tr>
                        <th class="text-center">№</th>
                        <th class="text-center">Название</th>
                        <th class="text-center">Описание</th>
                        <th class="text-center">Статус</th>
                        <th class="text-center">Дата готовности</th>
                        <th class="text-center">Заказчик</th>
                    </tr>
                    </thead>
                    <tbody id="prodReportTable" >
                    <c:forEach var="listValue" items="${projectList}" varStatus="сounter">
                        <tr >
                            <td class="text-center"><a href="/PmainWindowProjectAdmin?proj=${listValue.prId}">${сounter.count}</a></td>
                            <td class="text-center"><a href="/PmainWindowProjectAdmin?proj=${listValue.prId}">${listValue.title}</a></td>
                            <td class="text-center"><div class="table-responsive cell" >${listValue.description}</div></td>
                            <c:if test="${listValue.status == 'Есть просроченные задачи'}">
                                <td class="text-center danger">
                                        ${listValue.status}
                                    <c:if test="${listValue.dateOfReady ge now}">
                                        <p><i>До сдачи проекта осталось дней:</i> ${Math.round((listValue.dateOfReady.time-now.time) / (1000 * 60 * 60 * 24))}</p>
                                    </c:if>
                                </td>
                            </c:if>
                            <c:if test="${listValue.status != 'Есть просроченные задачи'&& listValue.status != 'Новый'}">
                                <td class="text-center">
                                        ${listValue.status}
                                    <c:if test="${listValue.dateOfReady ge now}">
                                        <p><i>До сдачи проекта осталось дней:</i> ${Math.round((listValue.dateOfReady.time-now.time) / (1000 * 60 * 60 * 24))}</p>
                                    </c:if>
                                </td>
                            </c:if>
                            <c:if test="${listValue.status == 'Новый'}">
                                <td class="text-center danger">
                                        ${listValue.status}
                                    <c:if test="${listValue.dateOfReady ge now}">
                                        <p><i>До сдачи проекта осталось дней:</i> ${Math.round((listValue.dateOfReady.time-now.time) / (1000 * 60 * 60 * 24))}</p>
                                    </c:if>
                                </td>
                            </c:if>
                            <td class="text-center"><c:set var = "date2" value="${listValue.dateOfReady}" />
                                <fmt:formatDate type = "date" value = "${date2}" /></td>
                            <td class="text-center">${clientList.pollFirst()}</td>
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

