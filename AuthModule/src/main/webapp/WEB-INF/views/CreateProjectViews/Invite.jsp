<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kinetik
  Date: 06.03.17
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css"/>
        <title>Приглашение модераторов</title>
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
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/PCabinClient">MePhorce</a>
        <nav class="nav nav-masthead">
            <a class="nav-link" href="/">Войти</a>
            <a class="nav-link" href="/aboutUs">О проекте</a>
            <a class="nav-link" href="/registrate">Создать аккаунт</a>
            <a class="nav-link" href="/contacts">Контакты</a>
        </nav>
    </div>
</nav>


<div class="container">
    <div class="сreate-project-block" id="inviting-moders">
        <h3> Приглашение модераторов </h3>
        </br>
        <form name="form1"  method="post">
       <div class="table-responsive">
           <table class="table table-striped">
               <thead>
               <tr>
                   <th class="text-center">Потенциальные модераторы</th>
                   <th class="text-center">Решение</th>
               </tr>
               </thead>
               <tbody id="prodReportTable" >
               <c:forEach var="listValue" items="${potentialList}">
                   <tr >
                       <td class="text-center"><a href="/getClientAdmin?modId=${listValue.modId}&prId=${project}">${listValue.modFamily} ${listValue.modFirstName} ${listValue.modSecName}</a></td>
                       <td class="text-center"><a href="/inviteAdminClient?modId=${listValue.modId}&prId=${project}">Пригласить в проект</a></td>
                   </tr>
               </c:forEach>
               </tbody>
           </table>
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