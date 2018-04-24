<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kinetik
  Date: 05.03.17
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Info Change Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <style type="text/css">
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/cssforpr.css" %>
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/bootstrap.min.css" %>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script type="text/javascript"
            src="<c:url value="/resources/autocomplete/jquery-2.1.1.js"/>"></script>
    <script src="<c:url value="/resources/autocomplete/jquery.autocomplete.min.js" />"></script>
    <script type="text/javascript">
        $(window).load(function()
        {
            $.each(${Skills}, function(idx, obj) {
                tabBody=document.getElementsByTagName("tbody").item(0);
                row=document.createElement("tr");
                row.id = obj.skilId;
                cell1 = document.createElement("td");
                cell2 = document.createElement("td");
                cell3 = document.createElement("td");
                var btn = document.createElement('input');
                btn.setAttribute("type", "button");
                btn.setAttribute("value", "Delete");
                btn.setAttribute("onclick", "deleteRow("+row.id+");")
                textnode1=document.createTextNode(obj.skillCat);
                textnode2=document.createTextNode(obj.skilName);
                cell1.appendChild(textnode1);
                cell2.appendChild(textnode2);
                cell3.appendChild(btn);
                row.appendChild(cell1);
                row.appendChild(cell2);
                row.appendChild(cell3);
                tabBody.appendChild(row);
            });
        });

    </script>
</head>
<body>
<script>
    $(document).ready(function() {
        $('#catSearch').autocomplete({
            serviceUrl: '/getSkillCats',
            paramName: "catName",
            delimiter: ",",
            transformResult: function(response) {
                return {
                    //must convert json to javascript object before process
                    suggestions: $.map($.parseJSON(response), function(item) {
                        return { value: item.catName, data: item.catId };
                    })
                };
            }
        });
    });
</script>
<c:url var="getSkillsByCat" value="/getSkills" />
<script type="text/javascript">
    $(document).ready(function() {
        $('#catSearch').change(
            function() {
                $.getJSON('${getSkillsByCat}', {
                    catName : $(this).val(),
                    ajax : 'true'
                }, function(data) {
                    var html = '';
                    var len = data.length;
                    for ( var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].skilId + '">'
                            + data[i].skilName + '</option>';
                    }
                    html += '</option>';
                    $('#skillList').html(html);
                });
            });
    });
</script>
<script type="text/javascript">
    function deleteRow(id)
    {
        var e = document.getElementById(id)
        e.parentNode.removeChild(e);
    }
</script>
<script type="text/javascript">
    function addRow()
    {
        if (!document.getElementsByTagName) return;
        content = document.getElementById("catSearch").value;
        tmp = document.getElementById("skillList")
        morecontent = tmp.options[tmp.selectedIndex].text;
        if (morecontent=="Выберите навык") return;
        if ($('#SkillTable td:contains(' + morecontent + ')').length) {
            return;
        }
        tabBody=document.getElementsByTagName("tbody").item(0);
        row=document.createElement("tr");
        row.id = tmp.options[tmp.selectedIndex].value;
        cell1 = document.createElement("td");
        cell2 = document.createElement("td");
        cell3 = document.createElement("td");
        var btn = document.createElement('input');
        btn.setAttribute("type", "button");
        btn.setAttribute("value", "Delete");
        btn.setAttribute("onclick", "deleteRow("+row.id+");")
        textnode1=document.createTextNode(content);
        textnode2=document.createTextNode(morecontent);
        cell1.appendChild(textnode1);
        cell2.appendChild(textnode2);
        cell3.appendChild(btn);
        row.appendChild(cell1);
        row.appendChild(cell2);
        row.appendChild(cell3);
        tabBody.appendChild(row);
    }
</script>
<script>
    function sendJson() {
        var settings = {
            "method": "POST",
            "url": "/setStudentFirstInSkills",
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(convertTable())
        };
        $.ajax(settings).done(function(response) {
            window.location="/PCabinStudentControl";
        }).fail(function (jqXHR, textStatus) {
            window.location = "/PCabinStudentControl";
        });
    }
</script>
<script>
    function convertTable() {
        var tbl = $('#SkillTable tr:has(td)').map(function() {
            var $tr = this;
            if ($tr.id != ""){
                return {
                    id: $tr.id
                }
            }
        }).get();
        return tbl;
    };
</script>

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

<div class="container">
    <div class="login-block">
        <h1>Выберите ваши тематики</h1>
        <input type="text" id="catSearch">
        <select class="form-control input-lg" id="skillList">
            <option value="">Выберите тематику</option>
        </select>
        <br>
        <br>
        <button onClick="addRow()">
            Добавить</button>
        <br>
        <br>
        <table class="skilltable" border='1' id='SkillTable'>
            <thead>
            <tr><td>Навык</td><td>Тематика</td><td>Действия</td></tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <br>
        <br>
        <button type="button" onclick="sendJson()">Принять</button>
        <br>
        <a href="/PCabinStudentControl">Заполнить позже</a>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <p class="text-muted">&copy;2017 Mephorce</p>
    </div>
</footer>

</body>
</html>