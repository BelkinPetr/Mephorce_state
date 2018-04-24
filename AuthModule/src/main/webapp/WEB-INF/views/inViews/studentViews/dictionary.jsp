<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Вероника
  Date: 13.05.2017
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/js/modal.js" var="modal"/>
    <spring:url value="/js/vendor/jquery-3.2.1.min.js" var="jQuery"/>
    <spring:url value="/js/vendor/jquery-ui-1.12.1/jquery-ui.min.js" var="jQuery_ui"/>
    <spring:url value="/js/vocabulary.js" var="vocabulary"/>
    <title>Словарь</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <style type="text/css">
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/cssforpr.css" %>
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/bootstrap.min.css" %>
        <%@include file="/WEB-INF/js/vendor/jquery-ui-1.12.1/jquery-ui.min.css"%>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
    <script src="${jQuery}"></script>
    <script src="${modal}"></script>
    <script src="${jQuery_ui}"></script>
    <script src="${vocabulary}"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
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
<div class="col-md-9">
    <div class="dictionary">
        <h2 class="section-caption">Словарь</h2>
        <hr>
        <div class="translation-block">
            <h3 class="word-caption">Слово</h3>
            <input id="vocabularyWord" class="word-input" type="text" name="word"/>
            <input id="translate" class="submit" type="submit" value="Перевести"/>
        </div>

        <div class="translation-block">
            <h3 class="word-caption">Значение</h3>
            <input id="first_translation" class="word-data translation" type="text" name="word" readonly/>
            <input id="second_translation" class="word-data translation" type="text" name="word" readonly/>
            <input id="third_translation" class="word-data translation" type="text" name="word" readonly/>
            <input id="user_translation" class="word-data translation" placeholder="Предложить перевод..." type="text" name="word"/>
            <input id="choose_translation" class="submit" type="submit" value="Выбрать">
        </div>

        <div class="translation-block">
            <h3 class="word-caption">Описание</h3>
            <input id="first_description" class="word-data" type="text" name="word" readonly/>
            <input id="second_description" class="word-data" type="text" name="word" readonly/>
            <input id="third_description" class="word-data" type="text" name="word" readonly/>
            <input id="user_description" class="word-data" placeholder="Предложить описание..." type="text" name="word"/>
            <input id="suggest_translation" class="submit" type="submit" value="Предложить">
        </div>
        <%--<div class="redex">
                <textarea class="form-control" maxlength="0" rows="15"></textarea>
            </div>

            <form class="form-inline">
                <p><a href="#" class="btn btn-info">Сохранить</a></p>


                <hr>

            </form>--%>


    </div>
</div>
<footer class="footer">
    <div class="container">
        <p class="text-muted">&copy;2017 Mephorce</p>
    </div>
</footer>
</body>
</html>
