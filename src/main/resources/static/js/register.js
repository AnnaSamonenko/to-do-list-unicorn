$(document).ready(function () {
    $("#signup").click(function () {
        $.ajax({
        type : "POST",
        contentType: "application/json; charset=utf-8",
        url :"http://localhost:8080/rest/user/add",
        data: JSON.stringify({
            username: $("#name").val(),
            email: $("#email").val(),
            password: $("#pass").val()
        }),
        timeout : 100000,
        success : function(response) {
            location.href = "http://localhost:8080/index"
        },
        error : function(e) {
            location.href = "http://localhost:8080/registration"
        }
        });
    });
});