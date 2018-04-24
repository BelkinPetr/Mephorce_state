<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css"/>
    <title>Создание проекта</title>
    <style type="text/css">
        <%@include file="/WEB-INF/css/bootstrap-4.0.0-alpha.6-dist/css/cssforpr.css" %>
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/bootstrap.min.css" %>
        <%@include file="/WEB-INF/css/zabuto_calendar.min.css" %>
    </style>
   <script src="../../js/vendor/jquery-3.2.1.min.js">
    </script>
    <script src="../../js/vendor/jquery.maskedinput.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/locale/ru.js"></script>
    <script src="WEB-INF/js/vendor/zabuto_calendar.min.js"></script>
</head>
<body>
<%--
    Все сообщения ${<название сообщения} буду ошибками в случае неправильного заполнения/ не заполнения поля, около которого появляется
--%>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">MePhorce</a>
        <nav class="nav nav-masthead">
            <a class="nav-link" href="/">Войти</a>
            <a class="nav-link" href="/aboutUs">О проекте</a>
            <a class="nav-link" href="/registrate">Создать аккаунт</a>
            <a class="nav-link" href="/contacts">Контакты</a>
        </nav>
    </div>
</nav>
<div class="container">
    <div class="create-project-block">
        <form:form method="post"  modelAttribute="Adder" action="/CreateProject">
            <h1>Новый проект</h1>
            <form:label path="cltitle"><div style="font-weight: bold">Название:<span class="required" style="color: #710009">*</span></div></form:label>
            <form:input path="cltitle" type="text" value="" placeholder=""/>${Tittle}   <br/>
            <form:label path="cldescription"><div style="font-weight: bold">Описание проекта:<span class="required" style="color: #710009">*</span></div></form:label> </br>
            <form:textarea path="cldescription" id="description" value="" placeholder="" required="true"/> ${Description}   <br/></br>
            <form:label path="cldescription"><div style="font-weight: bold">Дата начала работ по проекту:<span class="required" style="color: #710009">*</span></div></form:label>
            <form:input path="cldateOfCreation" type="date" name="calendar" value="" placeholder="" required="true"/> ${DateOfCreation}  <br/>
            <form:label path="cldescription"><div style="font-weight: bold">Планируемый срок окончания работ:<span class="required" style="color: #710009">*</span></div></form:label>
            <form:input path="cldateOfReady" type="date" name="calendar" value="" placeholder="" required="true"/>${DateOfReady}   <br/>
            <form:label path="cldescription"><div style="font-weight: bold">Бюджет проекта:<span class="required" style="color: #710009">*</span></div></form:label>
            <form:input path="clsum" type="text" value="" placeholder="Введите сумму по проекту"/>${Sum}   <br/>
            <form:label path="cldescription"><div style="font-weight: bold">Планируемый перечень работ:<span class="required" style="color: #710009">*</span></div></form:label>
            <form:textarea path="clworktypes" type="text" value="" placeholder=""/> ${WorkTypes}   <br/>
            ${errMessage}<br/>
            <form:button>Создать</form:button>
        </form:form>
        <script>
            $(function () {
                // идентификатор элемента (например: #datetimepicker1), для которого необходимо инициализировать виджет Bootstrap DateTimePicker
                // language=JQuery-CSS
                $('#datetimepicker1').datetimepicker();
            });
        </script>
    </div>
</div>
<footer class="footer">
    <div class="container">
        <span>&copy;2017 Mephorce</span>
    </div>
</footer>

</body>
</html>