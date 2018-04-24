"use strict";

$(document).ready(function () {
    var selectedTranslation;
    var translatingWord;
    var translated = false;

    if (!translated) {
        $('#suggest_translation').prop("disabled", true).hide();
    }

    $("#translate").click(function (e) {
        e.preventDefault();
        translatingWord = $("#vocabularyWord").val();
        console.log(translatingWord);
        $.getJSON("/vocabulary/" + translatingWord + "/")
            .done(function (data) {
                console.log("success");
                console.log(data);
                translated = true;
                $('#suggest_translation').prop("disabled", false).show();
                $('.selected').removeClass('selected');
                $('.empty').removeClass('empty');
                var translations = data.translations.filter(function (t) {
                    return t.status === "APPROVED";
                }).sort(function (a, b) {
                    return a.rating > b.rating ? -1 : 1;
                }).map(function (t) {
                    return t.translation;
                });

                var descriptions = data.descriptions.filter(function (d) {
                    return d.status === "APPROVED";
                }).sort(function (a, b) {
                    return a.rating > b.rating ? -1 : 1;
                }).map(function (d) {
                    return d.description;
                });

                if (translations.length == 0) {
                    $("#first_translation").toggleClass("empty");
                    $("#second_translation").toggleClass("empty");
                    $("#third_translation").toggleClass("empty");
                    $("#user_translation").toggleClass("selected");
                }

                if (descriptions.length == 0) {
                    $("#first_description").toggleClass("empty");
                    $("#second_description").toggleClass("empty");
                    $("#third_description").toggleClass("empty");
                    $("#user_description").toggleClass("selected");
                }

                $("#first_translation").val(translations[0]);
                $("#second_translation").val(translations[1]);
                $("#third_translation").val(translations[2]);

                $("#first_description").val(descriptions[0]);
                $("#second_description").val(descriptions[1]);
                $("#third_description").val(descriptions[2]);

            })
            .fail(function () {
                console.log("error");
            });
    });

    $("#first_translation, #second_translation, #third_translation").click(function (e) {
        e.preventDefault();
        selectedTranslation = $(e.target).val();
        if (selectedTranslation) {
            $('.selected').removeClass('selected');
            $(e.target).toggleClass('selected');
            console.log(selectedTranslation);
        }
    });

    $("#choose_translation").click(function (e) {
        e.preventDefault();
        if (selectedTranslation) {
            $.ajax({
                type: "PUT",
                url: "/vocabulary/" + translatingWord + "/select_translation",
                data: JSON.stringify({translation: selectedTranslation}),
                contentType: "application/json"
            })
                .done(function (data) {
                    console.log("success");
                    console.log(data);
                })
                .fail(function () {
                    console.log("error");
                })
                .always(function () {
                    $('.selected').removeClass('selected');
                    $('#choose_translation').prop('disabled', true).hide();
                })
        }
    });

    $('#suggest_translation').click(function (e) {
        e.preventDefault();
        var translation = $('#user_translation').val();
        var description = $('#user_description').val();

        if (translation || description) {
            $.ajax({
                type: "POST",
                url: "/vocabulary/" + translatingWord + "/suggest",
                data: JSON.stringify({translation: translation, description: description}),
                contentType: "application/json"
            })
                .done(function (data) {
                    console.log("success");
                    console.log(data);
                })
                .fail(function () {
                    console.log("error");
                })
                .always(function () {
                    $('#user_translation').val("");
                    $("#user_description").val("");
                })
        } else {
            $("#user_description").toggleClass("empty");
            $("#user_translation").toggleClass("empty");
        }
    })
});