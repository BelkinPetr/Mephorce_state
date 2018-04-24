<%--
  Created by IntelliJ IDEA.
  User: p_belkin
  Date: 18.03.2018
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title> Профиль пользователя </title>
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
        <a class="navbar-brand" href="/PCabinClient">MePhorce</a>
        <nav class="nav nav-masthead">
            <a class="nav-link" href="/PPersCabinClient"><span class="glyphicon glyphicon-user"> Профиль</span> </a>
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
            <a class="nav-link active" href="/"><span class="glyphicon glyphicon-log-out"></span></a>
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
        <div class="container">
            <div class="personal-page-block">
                <h1>
                    ${Client.clFamily}
                    ${Client.clFirstName}
                    ${Client.clSecName}
                </h1>
                <div class="col-md-6">
                    <div style=" width: 180px; height: 180px;">
                        <div class="photo-block">
                            <c:if test="${not empty photoList}">
                                <img src="/downPhotoforClient?fileName=${fn:replace(photoList[photoList.size()-1].photo_path,'\\','/')}" alt="" height="180"  >
                            </c:if>
                        </div>

                    </div>
                    <hr>
                    <form method="POST" action="uploadPhotoforClient" enctype="multipart/form-data">
                        <h4>Допустимые для загрузки типы: jpg, png</h4>
                        Файл для загрузки: <input type="file" name="photo"><br />
                        <input type="text" name="clID" value="${clId}" hidden="true">
                        <button value="Upload">
                            Загрузить </button>
                    </form>
                </div>
                <div class="col-md-6">
                    <div class="personal-info">
                        <div class="title" style="font-weight: bold"> <div class="m-b-5"></div> <h3> Общая информация</h3> <div class="pull-right m-b-5"><a href="/PClientChangeInfo"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Редактировать</a></div> </div>
                        <br>
                        <c:if test="${not empty Client.clFamily and Client.clFirstName and Client.clSecName}">
                            ФИО:&nbsp;&nbsp;${Client.clFamily}&nbsp;&nbsp;${Client.clFirstName}&nbsp;&nbsp;${Client.clSecName} <br>
                        </c:if>
                        <c:if test="${not empty Client.clBirthdate}">
                            <div class="m-b-10"><span class="info-label" style="font-weight: bold">Дата рождения:</span> ${Client.clBirthdate} </div>
                        </c:if>
                        <c:if test="${not empty Client.clHometown}">
                            <div class="m-b-10"><span class="info-label" style="font-weight: bold">Город проживания:</span> ${Client.clHometown} </div>
                        </c:if>
                        <c:if test="${not empty Client.clPhone}">
                            <div class="m-b-10"><span class="info-label" style="font-weight: bold">Телефон:</span> ${Client.clPhone} </div>
                        </c:if>
                        <c:if test="${not empty Client.clEmail}">
                            <div class="m-b-10"><span class="info-label" style="font-weight: bold">E-mail:</span> ${Client.clEmail} </div>
                        </c:if>
                        <div class="m-b-10"><span class="info-label" style="font-weight: bold">Статус:</span> Заказчик </div>
                        <br><br>
                        <h3>Сведения об организации</h3>
                        <br>
                        <c:if test="${not empty Client.clCompany}">
                            <div class="m-b-10"><span class="info-label" style="font-weight: bold">Название организации:</span> ${Client.clCompany} </div>
                        </c:if>
                        <c:if test="${not empty Client.clPos}">
                            <div class="m-b-10"><span class="info-label" style="font-weight: bold">Должность:</span> ${Client.clPos} </div>
                        </c:if>
                        <br><br>
                        <br>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="personal-page-block-skills">
                <h1> Проекты участника </h1>
                <div class="project-table">
                    <form class="form-inline">
                    <div class="table-responsive">
                        <table class="table table-striped tablesorter"  id="myTable" >
                            <thead>
                            <tr>
                                <th class="text-center">№</th>
                                <th class="text-center">Название</th>
                                <th class="text-center">Описание</th>
                                <th class="text-center">Статус</th>
                                <th class="text-center">Дата готовности</th>
                                <th class="text-center">Модератор</th>
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
                                    <c:if test="${listValue.status != 'Есть просроченные задачи' && listValue.status != 'Новый'}">
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
    </div>
</div>

<footer class="footer">
    <div class="container">
        <p class="text-muted">&copy;2017 Mephorce</p>
    </div>
</footer>
</body>
</html>
