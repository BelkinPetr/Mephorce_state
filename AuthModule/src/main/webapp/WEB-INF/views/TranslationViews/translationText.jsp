<%@ page import="HibernateEntities.translation.TranslationTextEntity" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Translate Text</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <spring:url value="/js/vendor/jquery-3.2.1.min.js" var="jQuery"/>
    <spring:url value="/js/vendor/jquery-ui-1.12.1/jquery-ui.min.js" var="jQuery_ui"/>
    <spring:url value="/js/modal.js" var="modal"/>

    <style type="text/css">
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/cssforpr.css" %>
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/bootstrap.min.css"%>
        <%@include file="/WEB-INF/js/vendor/jquery-ui-1.12.1/jquery-ui.min.css"%>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
    <script src="${jQuery}"></script>
    <script src="${jQuery_ui}"></script>
    <script src="${modal}"></script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">MePhorce</a>
        <nav class="nav nav-masthead">
            <a class="nav-link active" href="/">Войти</a>
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
                    <li class=""><a href="/PCabinStudentControl">Мои проекты</a></li>
                    <li class=""><a href="#">Чат с клиентом</a></li>
                    <li class=""><a href="#">Мои приглашения в проект</a></li>
                    <li class=""><a href="/PCabinStudent">Профиль</a></li>
                    <li class=""><a href="#">Сообщество</a></li>
                    <li class=""><a href="/">Выход</a></li>
                </form>
            </div>
            <hr>
            <div class="btn-group btn-group-vertical">
                <form>
                    <div class="projectmenu sidebar-header alert alert-info ">Проектное меню</div>
                    <li class=""><a href="/PMainWindowProject?prId=${prId}">Общая информация по проекту</a></li>
                    <li class=""><a href="/PStudentTasks?prId=${Task.projId}">Мои задачи</a></li>
                    <div class="dropdown">
                        <li class="dropdown">
                            <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                                Декомпозиция
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="/PProjectTasks?prId=${prId}">Проектные задачи</a></li>
                            </ul>
                        </li>
                        <div class="dropdown">
                            <li class="dropdown">
                                <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                                    Семантика проекта
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Исходный текст</a></li>
                                    <li><a href="/PConspect?prId=${prId}">Конспект</a></li>
                                    <li><a href="#">Нормы</a></li>
                                    <li><a href="#">Критерии</a></li>
                                    <li><a href="#">Схема сборки</a></li>
                                    <li><a href="/PDictionary?prId=${prId}">Словарь</a></li>
                                    <li><a href="#">Инструменты</a></li>
                                </ul>
                            </li>

                            <li class=""><a href="#">Сборка</a></li>
                            <li class=""><a href="#">Сдача</a></li>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="translation-text">
    <div class="container">
        <div class="text-block">
            <h2 class="caption">Исходный текст</h2>
            <textarea class="initial-text" name="initialText" id="initialText" readonly rows="20"
                      cols="50">${text.initialText}</textarea>
        </div>
        <div class="text-block">
            <h2 class="caption">Результирующий текст</h2>
            <form:form method="post" action="/translation" modelAttribute="text">
                <form:textarea path="resultText" class="initial-text" placeholder="Введите перевод текста..." rows="20"
                               cols="50"/>
                <div class="buttons">
                    <input class="submit" name="save" type="submit" value="Сохранить"/>
                    <%if (((TranslationTextEntity) request.getAttribute("text")).getStatus() != TranslationTextEntity.Status.PARTIALY_COMPLETED) {%>   <%-- OMFG --%>
                    <input class="submit" name="submit" type="submit" value="Сдать задание"/>
                    <%} else {%>
                    <input class="again" name="submit" type="submit" value="Сдать задание">
                    <%}%>
                </div>
            </form:form>
        </div>

        <div class="controls">
            <a href="#" id="fullText">Конспект</a>
            <a href="#" id="comments">Замечания</a>
            <a href="#" id="vocabulary">Словарь</a>
            <a href="#" id="standards">Нормы</a>
        </div>
    </div>

    <div class="comments" id="comments-modal" style="display: none">
        <table>
            <c:forEach items="${text.moderatorComments}" var="comment">
                <tr class="comment">
                    <td class="text"><c:out value="${comment.comment}"/></td>
                    <td class="sender"><c:out
                            value="${comment.moderator.modFirstName} ${comment.moderator.modFamily}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div id="standards-modal" style="display: none">
    <textarea class="initial-text full-text" name="standards" readonly rows="30"
              cols="60">${text.standards.standarts}</textarea>
    </div>

    <div id="fulltext-modal" style="display: none">
    <textarea class="initial-text full-text" name="fullText" readonly rows="30"
              cols="60">${text.fullText}</textarea>
    </div>

    <div id="vocabulary-modal" style="display: none">
        <jsp:include page="vocabulary.jsp"/>
    </div>
</div>
</body>
</html>
