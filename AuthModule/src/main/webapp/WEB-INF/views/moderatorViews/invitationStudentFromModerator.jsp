<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 26.03.2017
  Time: 15:07
  To change this template use File | Settings | File Templates .
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Main Window Project </title>
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
    <script>document.querySelector('select').value='Выберите принцип'</script>
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
            <hr>
            <div class="btn-group btn-group-vertical">
                <form>
                    <div class="sidebar-header alert alert-info ">Проектное меню</div>
                    <div class="dropdown">   <li class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                            Семантика проекта
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Конспект</a></li>
                            <li><a href="#">Правила</a></li>
                            <li><a href="#">Глоссарий</a></li>
                        </ul>
                    </li>
                        <li class="dropdown">
                            <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                                Задачи проекта
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Задача 1</a></li>
                                <li><a href="#">Задача 2</a></li>
                                <li><a href="#">Задача 3</a></li>
                            </ul>
                        </li>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-9">
<<<<<<< HEAD
        <form class="form-inline">
            <div class="">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th class="text-center">Потенциальные исполнители</th>
                        <th class="text-center">Решение</th>
                    </tr>
                    </thead>
                    <tbody >
                    <c:forEach var="listValue" items="${potentialList}">
                        <tr >
                            <td class="text-center"><a href="/getStudentAdmin?stId=${listValue.studentsId}&prId=${project}">${listValue.stFamily} ${listValue.stFirstName} ${listValue.stSecName}</a></td>
                            <td class="text-center"><a href="/inviteStudentAdmin?stId=${listValue.studentsId}&prId=${project}">Пригласить в проект</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="wind col-md-offset-2">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th class="text-center">Черный список</th>
                    </tr>
                    </thead>
                    <tbody >
                    <c:forEach var="listValue" items="${blackList}">
                        <tr >
                            <td class="text-center"><a href="/getStudentAdmin?stId=${listValue.studentsId}&prId=${project}">${listValue.stFamily} ${listValue.stFirstName} ${listValue.stSecName}</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
=======
        <h3>Приглашение исполнителей</h3>
        <c:if test="${project1.personNumber gt slavesNumber}">
            <h4>Вам необходимо исполнителей: <c:out value="${project1.personNumber-slavesNumber}"/></h4>
        </c:if>
        <c:if test="${project1.personNumber le slavesNumber}">
            <h4>Ваш проект укомплектован</h4>
        </c:if>
        <div class="wind">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th class="text-center">Потенциальные исполнители</th>
                    <th class="text-center">Решение</th>
                </tr>
                </thead>
                <tbody >
                <c:forEach var="listValue" items="${potentialList}">
                    <tr >
                        <td class="text-center"><a href="/PViewCabinStudent?studentId=${listValue.studentsId}">${listValue.stFamily} ${listValue.stFirstName} ${listValue.stSecName}</a></td>
                        <td class="text-center"><a href="/inviteStudentAdmin?stId=${listValue.studentsId}&prId=${project}">Пригласить в проект</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="wind">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th class="text-center">Черный список</th>
                    <th class="text-center">Решение</th>
                </tr>
                </thead>
                <tbody >
                <c:forEach var="listValue" items="${blackList}">
                    <tr >
                        <td class="text-center"><a href="/PViewCabinStudent?studentId=${listValue.studentsId}">${listValue.stFamily} ${listValue.stFirstName} ${listValue.stSecName}</a></td>
                        <td class="text-center"><a href="/inviteStudentAdmin?stId=${listValue.studentsId}&prId=${project}">Пригласить в проект</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
>>>>>>> sasha_branch
        <div class="wind">
            <table class="table table-striped" id="myTable4">
                <thead>
                <tr>
                    <th class="text-center">Ожидание ответа</th>
<<<<<<< HEAD
=======
                    <th class="text-center">Прекратить ожидание</th>
>>>>>>> sasha_branch
                    <th class="text-center"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="listValue" items="${noAnswerList}">
                    <tr >
<<<<<<< HEAD
                        <td class="text-center"><a href="/getStudentAdmin?stId=${listValue.studentsId}&prId=${project}">${listValue.stFamily} ${listValue.stFirstName} ${listValue.stSecName}</a></td>
=======
                        <td class="text-center"><a href="//PViewCabinStudent?studentId=${listValue.studentsId}">${listValue.stFamily} ${listValue.stFirstName} ${listValue.stSecName}</a></td>
                        <td class="text-center"><a href="/denyStudent?stId=${listValue.studentsId}&prId=${project}">Отказать</a></td>
>>>>>>> sasha_branch
                        <td class="text-center"><a href="/addStudentToBlackList?stId=${listValue.studentsId}&prId=${project}">Добавить в черный список</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="">
            <table class="table" id="myTable5">
                <thead>
                <tr>
                    <th class="text-center">Ответившие</th>
                    <th class="text-center">Ответ</th>
<<<<<<< HEAD
                    <th class="text-center"></th>
=======
                    <th class="text-center">Статус</th>
>>>>>>> sasha_branch
                    <th class="text-center"></th>
                    <th class="text-center"></th>
                </tr>
                </thead>
                <tbody id="prodReportTable1">
<<<<<<< HEAD
                <c:forEach var="listValue" items="${acceptList}">
                    <tr >
                        <td class="text-center"><a href="/getStudentAdmin?stId=${listValue.studentsId}&prId=${project}">${listValue.stFamily} ${listValue.stFirstName} ${listValue.stSecName}</a></td>
=======
                <c:forEach var="listValue" items="${Slaves}">
                    <tr >
                        <td class="text-center"><a href="/PViewCabinStudent?studentId=${listValue.studentsId}">${listValue.stFamily} ${listValue.stFirstName} ${listValue.stSecName}</a></td>
                        <td class="text-center">Принято</td>
                        <td class="text-center">Добавлен в проект</td>
                        <td class="text-center"></td>
                        <td class="text-center"></td>
                    </tr>
                </c:forEach>
                <c:forEach var="listValue" items="${acceptList}">
                    <tr >
                        <td class="text-center"><a href="/PViewCabinStudent?studentId=${listValue.studentsId}">${listValue.stFamily} ${listValue.stFirstName} ${listValue.stSecName}</a></td>
>>>>>>> sasha_branch
                        <td class="text-center">Принято</td>
                        <td class="text-center"><a href="/addStudentToProject?stId=${listValue.studentsId}&prId=${project}">Добавить в проект</a></td>
                        <td class="text-center"><a href="/denyStudent?stId=${listValue.studentsId}&prId=${project}">Отказать</a></td>
                        <td class="text-center"><a href="/addStudentToBlackList?stId=${listValue.studentsId}&prId=${project}">Добавить в черный список</a></td>
                    </tr>
                </c:forEach>
                <c:forEach var="listValue" items="${denyList}">
                    <tr >
<<<<<<< HEAD
                        <td class="text-center"><a href="/getStudentAdmin?stId=${listValue.studentsId}&prId=${project}">${listValue.stFamily} ${listValue.stFirstName} ${listValue.stSecName}</a></td>
                        <td class="text-center">Отказано</td>
                        <td class="text-center">Добавление невозможно</td>
                        <td class="text-center"></td>
                        <td class="text-center"><a href="/addStudentToBlackList?stId=${listValue.studentsId}&prId=${project}">Добавить в черный список</a
=======
                        <td class="text-center"><a href="/PViewCabinStudent?studentId=${listValue.studentsId}">${listValue.stFamily} ${listValue.stFirstName} ${listValue.stSecName}</a></td>
                        <td class="text-center">Отказано</td>
                        <td class="text-center">Добавление невозможно</td>
                        <td class="text-center"></td>
                        <td class="text-center"><a href="/addStudentToBlackList?stId=${listValue.studentsId}&prId=${project}">Добавить в черный список</a></td>
>>>>>>> sasha_branch
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
<<<<<<< HEAD
        <p><a href="/PmainWindowProjectAdmin?proj=${project}"class="btn btn-info">Вернуться к проекту</a></p>
        <form class="form-inline">
            <p><a href="#"class="">Отзывы модераторов</a></p>
            <div class="col-md-offset-2">
                <p><a href="#"class="">Чат</a></p>
=======
        <p><a href="/PmainWindowProjectAdmin?proj=${project}"class="btn btn-info btn-lg">Вернуться к проекту</a></p>
        <form class="form-inline">
            <p><h4><a href="#"class="">Отзывы модераторов</a></h4></p>
            <div class="col-md-offset-2">
                <p><h4><a href="#"class="">Чат с исполнителем</a></h4></p>
>>>>>>> sasha_branch
            </div>
        </form>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <p class="text-muted">&copy;2017 Mephorce</p>
    </div>
</footer>
</body>
</html>
