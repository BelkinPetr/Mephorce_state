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
    <title> Personal Admin Page2 </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

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
                        <li><a href="/PFileAdmin?projId=${project.prId}">Файлы проекта</a></li>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-9">
        <h3>Общая информация</h3>
        <h4>Тематика проекта:&ensp;${topic}</h4>
        <h4>Название проекта: ${project.title}</h4>
        <h4>Описание проекта: ${project.description}</h4>
        <h4>Принцип распределения денег: ${moneyDivision}</h4>
        <h4>Дата начала:<c:set var = "date1" value="${project.dateOfCreation}" />
            <fmt:formatDate type = "date" value = "${date1}" /></h4>
        <h4>Дата окончания:<c:set var = "date2" value="${project.dateOfReady}" />
            <fmt:formatDate type = "date" value = "${date2}" /></h4>
        <h4>Заказчик проекта:&ensp;${Client.clFamily} ${Client.clFirstName} ${Client.clSecName}</h4>
        <h4>Сумма:&ensp;${project.sum} руб.</h4>
        <hr>
        <h3>Комплектация проекта</h3>
        <form class="form-inline">
            <h4>Статус комплектации:</h4>
            <div class="shadow">
                <c:if test="${project.personNumber gt slavesNumber}">
                    <span class="badge badge-danger">${complection}</span>
                </c:if>
                <c:if test="${project.personNumber le slavesNumber}">
                    <span class="badge badge-success">${complection}</span>
                </c:if>
            </div>
        </form>
        <h4>Необходимое число исполнителей:&ensp;${project.personNumber}</h4>
        <h4>Фактическое число исполнителей:&ensp;${slavesNumber}</h4>
        <h4>Задать количество исполнителей:&ensp;
            <form:form method="post" modelAttribute="ProjectChanger" action="/changeProjectInfoByAdmin1?prId=${project.prId}">
                <form:input path="numberOfPeople" type="text" value="" placeholder="Количество человек в проекте"/>
                <form:button><h4>Сохранить изменения</h4></form:button>
            </form:form>
        </h4>
        <hr>
        <h3>Исполнители проекта</h3>
        <div class="wind">
            <table class="table table-striped" id="myTable1">
                <thead>
                <tr>
                    <th class="text-center">Исполнители, участвующие в проекте</th>
                </tr>
                </thead>
                <tbody id="prodReportTable1">
                <c:forEach var="listValue" items="${Slaves}">
                    <tr >
                        <td class="text-center">
                            ${listValue.stFamily} ${listValue.stFirstName} ${listValue.stSecName}
                            <c:if test="${project.status eq 'Завершен'}">
                                &nbsp;&nbsp;&nbsp;
                                <a href="/rateStudent?stId=${listValue.studentsId}&topic=${topic}&proj=${project.prId}&modId=${Admin.modId}">Оценить</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <p><a href="/PAdminInvitation?proj=${project.prId}"class="btn btn-info btn-lg">Пригласить</a></p>
        <p><h4><a href="#"class="">Чат с исполнителем</a></h4></p>
        <hr>
        <h3>Принцип распределения денег</h3>
        <form:form method="post" modelAttribute="ProjectChanger" action="/changeProjectInfoByAdmin2?prId=${project.prId}">
            <form:select path="moneyDiv">
                <form:option value="0">Поровну</form:option>
                <form:option value="1">По коэффициенту трудового участия</form:option>
                <form:option value="2">По первым десяти местам</form:option>
                <form:option value="3">По результатам работы</form:option>
            </form:select>
            <form:button><h4>Сохранить изменения</h4></form:button>
        </form:form>
        <hr>
        <h3>Задачи проекта</h3>
        <form class="form-inline">
            <div class="">
                <table class="table table-striped"  id="myTable2" >
                    <tbody id="prodReportTable2" >
                    <tr >
                        <td class="text-center">Задача 1</td>
                        <td class="text-center">Задача 2</td>
                        <td class="text-center">Задача 3</td>
                    </tbody>
                </table>
            </div>
        </form>
        <form class="form-inline">
            <p><a href="#"class="btn btn-info btn-lg">Открыть задачу</a></p>
            <div class="col-md-offset-1">
                <p><h4><a href="#"class="">Чат с заказчиком</a></h4></p>
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
