<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/js/modal.js" var="modal"/>
    <spring:url value="/js/vendor/jquery-3.2.1.min.js" var="jQuery"/>
    <spring:url value="/js/vendor/jquery-ui-1.12.1/jquery-ui.min.js" var="jQuery_ui"/>
    <spring:url value="/js/vocabulary.js" var="vocabulary"/>
    <style type="text/css">
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/cssforpr.css" %>
        <%@include file="/WEB-INF/bootstrap-4.0.0-alpha.6-dist/css/bootstrap.min.css"%>
        <%@include file="/WEB-INF/js/vendor/jquery-ui-1.12.1/jquery-ui.min.css"%>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
    <script src="${jQuery}"></script>
    <script src="${modal}"></script>
    <script src="${jQuery_ui}"></script>
    <script src="${vocabulary}"></script>
</head>
<body>
<div>
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
</div>
</body>
</html>
