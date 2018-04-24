<%--suppress JSUnresolvedFunction --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: Кинетик
  Date: 28.12.2016
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Страница студента</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <style type="text/css">
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/cssforpr.css" %>
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/bootstrap.min.css" %>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <c:url var="getGroups" value="/loadGroups" />
    <script type="text/javascript">
        $(document).ready(
            function() {
                $.getJSON('${getGroups}', {
                    ajax : 'true'
                }, function(data) {
                    var html = '<option value="">Выберите группу</option>';
                    var len = data.length;
                    for ( var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].groupId + '">'
                            + data[i].groupName + '</option>';
                    }
                    html += '</option>';
                    $('select#IdGroupPart').html(html);
                });
            });
    </script>

    <c:url var="getStudents" value="/loadStudents" />
    <script type="text/javascript">
        $(document).ready(function() {
            $('#IdGroupPart').change(
                function() {
                    $.getJSON('${getStudents}', {
                        groupId : $(this).val(),
                        ajax : 'true'
                    }, function(data) {
                        var html = '<option value="">Выберите студента</option>';
                        var len = data.length;
                        for ( var i = 0; i < len; i++) {
                            html += '<option value="' + data[i].studentsId + '">'
                                + data[i].stFamily  + ' ' + data[i].stFirstName + '</option>';
                        }
                        html += '</option>';
                        $('#IdStudentPart').html(html);
                    });
                });
        });
</script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#IdStudentPart").change(onSelectChange);
        });
        function onSelectChange() {
            var selected = $("#IdStudentPart option:selected");
            var output = "";
            if(selected.val() != 0){
                output = "Вы выбрали студента " + selected.text();
            }
            $("#output").html(output);
        }
    </script>
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

<div class="logo"></div>
<div class="login-block">

    <h1>Вход как Исполнитель</h1>
    <div id="output"></div>
    <form:form id="signUp" method="post" modelAttribute="studentLogin" action="checkStudent">
    <fieldset>
        <form:select id="IdGroupPart" path="">
        </form:select>
        <form:select id="IdStudentPart" path="name">
            <form:option value="">Выберите студента</form:option>
        </form:select>
    </fieldset>
        <form:input path="password" type="password" value="" placeholder="Пароль" id="password"/>${passMessage}<br/>
        <form:button>Войти</form:button>
    </form:form>
</div>

<footer class="footer">
    <div class="container">
        <p class="text-muted">&copy;2017 Mephorce</p>
    </div>
</footer>

</body>
</html>

