<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title> Personal Admin Page1 </title>
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
                    <li class=""><a href="/GoToInvite?modId=${Admin.modId}">Мои приглашения в проект</a> </li>
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
                    ${Admin.modFamily}
                    ${Admin.modFirstName}
                    ${Admin.modSecName}
                </h1>
                <div class="col-md-6">
                    <div style=" width: 180px; height: 180px;">
<div class="photo-block">
<c:if test="${not empty photoList}">
        <img src="/downPhoto?fileName=${fn:replace(photoList[photoList.size()-1].photo_path,'\\','/')}" alt="" height="180"  >
</c:if>
</div>

                    </div>
                    <hr>
                    <form method="POST" action="uploadPhoto" enctype="multipart/form-data">
                        <h4>Допустимые для загрузки типы: jpg, png</h4>
                        Image to upload: <input type="file" name="photo"><br />
                        <input type="text" name="modID" value="${modId}" hidden="true">
                        <input type="submit" value="Upload">
                        Press here to upload the image!
                    </form>
                </div>
                <div class="col-md-6">
                    <div class="personal-info">
                        <b>Контактная информация:</b>
                        <br>
                        <c:if test="${not empty Admin.modPhone}">
                            Телефон:&nbsp;&nbsp;${Admin.modPhone} <br>
                        </c:if>
                        <c:if test="${not empty Admin.modEmail}">
                            E-mail:&nbsp;&nbsp;${Admin.modEmail} <br><br>
                        </c:if>
                        <b>Информация о себе:</b>
                        <br>
                        <c:if test="${not empty Admin.modHometown}">
                            Город:&nbsp;&nbsp;${Admin.modHometown} <br>
                        </c:if>
                            Статус:&nbsp;&nbsp;Модератор
                        <br><br>
                        <a href="/PAdminChangeInfo">Изменить</a>
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
                        <table class="table table-striped tablesorter"  id="myTable1" >
                            <thead>
                            <tr>
                                <th class="text-center">Категория</th>
                                <th class="text-center">Навык</th>
                                <th class="text-center">Рейтинг</th>
                            </thead>
                            <tbody id="prodReportTable1" >
                            <c:forEach items="${Skills}" var="entry">
                                <tr >
                                    <td class="text-center">${entry.value.skillCat}</td>
                                    <td class="text-center">${entry.key}</td>
                                    <td class="text-center">${entry.value.rating}</td>
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
                        <h3>Новых проектов</h3>
                        <div class="shadow">
                            <span class="badge badge-info">${fn:length(projectList)}</span>
                        </div>
                    </form>
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
