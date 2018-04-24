<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Вероника
  Date: 13.05.2017
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Window of SemTask</title>
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
                    <li class=""> <a href="/PMainWindowProject?prId=${SemTask.projId}">Общая информация по проекту</a></li>
                    <li class=""><a href="/PStudentTasks?prId=${SemTask.projId}">Мои задачи</a> </li>
                    <div class="dropdown">   <li class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                            Декомпозиция
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/PProjectTasks?prId=${SemTask.projId}">Проектные задачи</a></li>
                        </ul>
                    </li>
                        <div class="dropdown">   <li class="dropdown">
                            <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                                Семантика проекта
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="/PStarttext?prId=${SemTask.projId}">Исходный текст</a></li>
                                <li><a href="/PConspect?prId=${SemTask.projId}">Конспект</a></li>
                                <li><a href="/PNorms?prId=${SemTask.projId}">Нормы</a></li>
                                <li><a href="/PKriterii?prId=${SemTask.projId}">Критерии</a></li>
                                <li><a href="/PDictionary?prId=${SemTask.projId}">Словарь</a></li>
                                <li><a href="/PInstrumenty?prId=${SemTask.projId}">Инструменты</a></li>
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
    <h3>Задача ${SemTask.taskId} ${SemTask.title}</h3>
    <hr>
    <h4>Статус: ${SemTask.status}</h4>
    <h4>Описание: ${SemTask.desctiption}</h4>
    <h4>Приоритет: ${SemTask.prioritet}</h4>
    <h4>Дата создания: ${SemTask.dateOfCreation}</h4>
    <h4>Дата окончания: ${SemTask.dateOfFinish}</h4>
    <h4>Стадия проверки: ${SemTaskModer}</h4>
    <hr>

    <div class="redex">
        <form:form modelAttribute="TextField" method="post" action="/saveSemTask?taskId=${SemTask.taskId}" style="font-size: 25px; margin-bottom: 5px; position: static !important">
            <form:input path="result" class="redex" type="text" placeholder="${SemTask.results}" style="font-size: 25px; margin-bottom: 5px; position: static !important"></form:input> <br/>
            <form:button class="btn btn-info"  style="margin-bottom: 5px">Сохранить</form:button>
            <div class="">
                <a href="/sendToModer1?taskId=${SemTask.taskId}" class="btn btn-info">Отправить на проверку</a> </p>
            </div>
        </form:form>
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
