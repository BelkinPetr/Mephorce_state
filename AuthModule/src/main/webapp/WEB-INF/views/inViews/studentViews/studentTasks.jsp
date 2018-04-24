<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Вероника
  Date: 13.05.2017
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My tasks</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <style type="text/css">
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/cssforpr.css" %>
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/bootstrap.min.css" %>
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
    <div class="col-md-3">
        <div class="user-info-and-menu">
            <div class="navbar-header header">
                <h1>
                    ${Student.stFamily}
                    ${Student.stFirstName}
                    ${Student.stSecName}
                </h1>
                <hr>
            </div>
            <div class="btn-group btn-group-vertical">
                <form>
                    <div class="sidebar-header alert alert-info ">Меню</div>
                    <li class=""> <a href="/PCabinStudentControl">Мои проекты</a></li>
                    <li class=""><a href="#">Чат с клиентом</a> </li>
                    <li class=""><a href="#">Мои приглашения в проект</a> </li>
                    <li class=""><a href="/PCabinStudent">Профиль</a> </li>
                    <li class=""><a href="#">Сообщество</a> </li>
                    <li class=""><a href="/">Выход</a> </li>
                </form>
            </div>
            <hr>
            <div class="btn-group btn-group-vertical">
                <form>
                    <div class="projectmenu sidebar-header alert alert-info ">Проектное меню</div>
                    <li class=""> <a href="/PMainWindowProject?prId=${prId}">Общая информация по проекту</a></li>
                    <li class=""><a href="/PStudentTasks?prId=${prId}">Мои задачи</a> </li>
                    <div class="dropdown">   <li class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                            Декомпозиция
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/PProjectTasks?prId=${prId}">Проектные задачи</a></li>
                        </ul>
                    </li>
                        <div class="dropdown">   <li class="dropdown">
                            <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                                Семантика проекта
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="/PStarttext?prId=${prId}">Исходный текст</a></li>
                                <li><a href="/PConspect?prId=${prId}">Конспект</a></li>
                                <li><a href="/PNorms?prId=${prId}">Нормы</a></li>
                                <li><a href="/PKriterii?prId=${prId}">Критерии</a></li>
                                <li><a href="/PDictionary?prId=${prId}">Словарь</a></li>
                                <li><a href="/PInstrumenty?prId=${prId}">Инструменты</a></li>
                            </ul>
                        </li>

                            <li class=""><a href="#">Сборка</a> </li>
                            <li class=""><a href="#">Сдача</a> </li>
                        </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="col-md-9">
    <div class="project-table">
        <h2>Название проекта: ${Project.title}
        </h2>
        <h2>Мои проектные задачи</h2>
        <hr>
        <table class="table table-striped  tablesorter">
            <thead>
            <tr>
                <th class="text-center">№</th>
                <th class="text-center">Название</th>
                <th class="text-center">Описание</th>
                <th class="text-center">Статус</th>
                <th class="text-center">Приоритет</th>
                <th class="text-center">Дата создания</th>
                <th class="text-center">Дата окончания</th>
            </tr>
            </thead>
            <tbody id="prodReportTable">
            <%-- <c:forEach var="listValue" items="${projects}">
            --%>

            <c:forEach var="listValue" items="${Tasks}" varStatus="counter">
                <tr>
                    <td class="text-center"><a href="/PMainWindowTask?taskId=${listValue.taskId}">${counter.count}</a></td>
                    <td class="text-center"><a href="/PMainWindowTask?taskId=${listValue.taskId}">${listValue.title}</a></td>
                    <td class="text-center"><a href="/PMainWindowTask?taskId=${listValue.taskId}">${listValue.desctiption}</a></td>
                    <td class="text-center">${listValue.status}</td>
                    <td class="text-center">${listValue.prioritet}</td>
                    <td class="text-center">${listValue.dateOfCreation}</td>
                    <td class="text-center">${listValue.dateOfFinish}</td>

                </tr>
            </c:forEach>
            <%--<tr>
                <td class="text-center"><a href="/PMainWindowTask2">2</a></td>
                <td class="text-center"><a href="/PMainWindowTask2">Конспект</a></td>
                <td class="text-center"><a href="/PMainWindowTask2">Законспектировать часть текста</a></td>
                <td class="text-center">Решена</td>
                <td class="text-center">Низкий</td>
                <td class="text-center">${listValue.dateOfCreation}</td>
                <td class="text-center">${listValue.dateOfReady}</td>

            </tr>
             <%-- </c:forEach>
             --%>
            </tbody>
        </table>
    </div>

    <div class="project-table">
        <h2>Семантические задачи</h2>
        <hr>
        <table class="table table-striped  tablesorter">
            <thead>
            <tr>
                <th class="text-center">№</th>
                <th class="text-center">Название</th>
                <th class="text-center">Описание</th>
                <th class="text-center">Статус</th>
                <th class="text-center">Приоритет</th>
                <th class="text-center">Дата создания</th>
                <th class="text-center">Дата окончания</th>
            </tr>
            </thead>
            <tbody id="prodReportTablee">
            <%-- <c:forEach var="listValue" items="${projects}">
            --%>

            <c:forEach var="listValue" items="${SemanticTasks}" varStatus="counter">
                <tr>
                    <td class="text-center"><a href="/PMainWindowSemTask?taskId=${listValue.taskId}">${counter.count}</a></td>
                    <td class="text-center"><a href="/PMainWindowSemTask?taskId=${listValue.taskId}">${listValue.title}</a></td>
                    <td class="text-center"><a href="/PMainWindowSemTask?taskId=${listValue.taskId}">${listValue.desctiption}</a></td>
                    <td class="text-center">${listValue.status}</td>
                    <td class="text-center">${listValue.prioritet}</td>
                    <td class="text-center">${listValue.dateOfCreation}</td>
                    <td class="text-center">${listValue.dateOfFinish}</td>

                </tr>
            </c:forEach>
            <%--<tr>
                <td class="text-center"><a href="/PMainWindowTask2">2</a></td>
                <td class="text-center"><a href="/PMainWindowTask2">Конспект</a></td>
                <td class="text-center"><a href="/PMainWindowTask2">Законспектировать часть текста</a></td>
                <td class="text-center">Решена</td>
                <td class="text-center">Низкий</td>
                <td class="text-center">${listValue.dateOfCreation}</td>
                <td class="text-center">${listValue.dateOfReady}</td>

            </tr>
             <%-- </c:forEach>
             --%>
            </tbody>
        </table>
    </div>

</div>
</div>

</head>
<body>
<footer class="footer">
    <div class="container">
        <p class="text-muted">&copy;2017 Mephorce</p>
    </div>
</footer>
</body>
</html>
