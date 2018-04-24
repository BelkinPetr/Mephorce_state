"use strict";

$(document).ready(function() {
    $("#fullText").click(function (e) {
        e.preventDefault();
        $("#fulltext-modal").css("display", "");
        $("#fulltext-modal").dialog({
            width: 800,
            minHeight: 300,
            title: "Конспект",
            draggable: false,
            modal: true});
    });

    $("#standards").click(function (e) {
        e.preventDefault();
        $("#standards-modal").css("display", "");
        $("#standards-modal").dialog({
            width: 800,
            minHeight: 300,
            title: "Нормы",
            draggable: false,
            modal: true});
    });

    $("#vocabulary").click(function (e) {
        e.preventDefault();
        $("#vocabulary-modal").css("display", "");
        $("#vocabulary-modal").dialog({
            minWidth: 900,
            minHeight: 400,
            title: "Словарь",
            draggable: false,
            modal: true});
    });

    $("#comments").click(function (e) {
        e.preventDefault();
        $("#comments-modal").css("display", "");
        $("#comments-modal").dialog({
            minWidth: 500,
            title: "Коментарии",
            draggable: false,
            modal: true
        });
    });

    $("#comments-modal").find(".comment").click(function (e) {
        e.preventDefault();
        $(e.target).toggleClass('selected');
        console.log("selected");
    })
});