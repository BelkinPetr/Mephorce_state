<%--
  Created by IntelliJ IDEA.
  User: Irishka
  Date: 16.05.2017
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title> Files </title>
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
                    <li class=""> <a href="/PCabinAdmin">Мои проекты</a></li>
                    <li class=""><a href="#">Чат с клиентом</a> </li>
                    <li class=""><a href="#">Мои приглашения в проект</a> </li>
                    <li class=""><a href="/PPersCabinAdmin">Профиль</a> </li>
                    <li class=""><a href="#">Сообщество</a> </li>
                    <li class=""><a href="/">Выход</a> </li>
                </form>
            </div>
            <hr>
            <div class="btn-group btn-group-vertical">
                <form>
                    <div class="sidebar-header alert alert-info ">Проектное меню</div>
                    <li class=""> <a href="/PmainWindowProjectAdmin?proj=${projId}">Информация</a></li>
                    <div class="dropdown">   <li class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                            Декомпозиция
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Задачи</a></li>
                            <li><a href="#">Рассылка</a></li>
                        </ul>
                    </li>
                        <li class=""><a href="/PAdminInvitation">Исполнение</a> </li>
                        <li class=""><a href="#">Сборка</a> </li>
                        <li class=""><a href="#">Сдача</a> </li>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="col-sm-9">
        <div class="container">
            <div class="files-block">
                <h1>Файлы проекта</h1>
                <c:if test="${not empty fileList}">
                    <table>
                        <thead>
                        <tr>
                            <th>Название файла</th>
                            <th>Путь к файлу</th>
                            <th>Ид проекта</th>
                            <th>Автор</th>
                        </tr>
                        </thead>
                        <tbody id="prodReportTable">
                        <c:forEach var="listValue" items="${fileList}">
                            <tr>

                                <td><a href="/down?fileName=${fn:replace(listValue.file_path,'\\','/')}" target="_blank">${listValue.file_name}</a></td>
                                <td>${listValue.file_path}</td>
                                <td>${listValue.prId}</td>
                                <td>${Admin.modFamily} ${Admin.modFirstName} ${Admin.modSecName}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
            <hr>
            <form method="POST" action="uploadFil" enctype="multipart/form-data">
                <h4>Допустимые для загрузки типы файлов: pdf, docx, jpg, png</h4>
                File to upload: <input type="file" name="file"><br />
                <input type="text" name="prID" value="${projId}" hidden="true">
                <input type="submit" value="Upload">
                Press here to upload the file!
            </form>
        </div>
    </div>
</div>
