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
    <title>Приглашение модераторов</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <style type="text/css">
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/cssforpr.css" %>
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/bootstrap.min.css" %>
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/table-style.css" %>
    </style>

    <script>

    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script type="text/javascript" >

    </script>
</head>
<body>
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
    <div class="login-block" id="vasya">
        <h1> Приглашение модераторов </h1>
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
               <tbody >
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