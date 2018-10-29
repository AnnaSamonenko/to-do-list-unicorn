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
            //document.location.href = "http://localhost:8080/index";
            alert("something");
        },
        error : function(e) {
            document.location.href = "http://localhost:8080/registration"
        }
        });
    });
});