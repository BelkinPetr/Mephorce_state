<%--
  Created by IntelliJ IDEA.
  User: postgres
  Date: 30.05.2017
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
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
    <title> Personal Admin Page </title>
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
                    <li class=""><a href="/PCabinAdmin">Мои проекты</a></li>
                    <li class=""><a href="#">Чат с клиентом</a> </li>
                    <li class=""><a href="#">Мои приглашения в проект</a> </li>
                    <li class=""><a href="/PPersCabinAdmin">Профиль</a> </li>
                    <li class=""><a href="/PCommunityNewsAdmin">Сообщество</a> </li>
                    <li class=""><a href="/">Выход</a> </li>
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-9">
        <div class="project-table">
            <h5>Мои проекты</h5>
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
                        <th class="text-center">Сумма</th>
                        <th class="text-center">Заказчик</th>
                    </tr>
                    </thead>
                    <tbody id="prodReportTable" >
                    <c:forEach var="listValue" items="${Project}">
                        <tr >
                            <td class="text-center">${listValue.prId}</td>
                            <td class="text-center">${listValue.title}</td>
                            <td class="text-center"><div class="table-responsive cell" >${listValue.description}</div></td>
                            <td class="text-center">${listValue.dateOfCreation}</td>
                            <td class="text-center">${listValue.status}</td>
                            <td class="text-center">${listValue.dateOfReady}</td>
                            <td class="text-center">${listValue.sum}</td>
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