$(document).ready(function () {
    $("#signup").click(function () {
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8080/api/user/add",
            data: JSON.stringify({
                username: $("#name").val(),
                email: $("#email").val(),
                password: $("#pass").val()
            }),
            timeout: 100000,
            success: function (response) {
            },
            error: function (e) {
            }
        });
    });
});