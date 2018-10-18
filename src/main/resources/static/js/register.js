$(document).ready(function () {
    $("#signup").click(function () {
        $.ajax({
        type : "POST",
        contentType: "application/json; charset=utf-8",
        url :"http://localhost:8080/rest/user/add",
        data: JSON.stringify({
            login: $("#name").val(),
            email: $("#email").val(),
            password: $("#pass").val()
        }),
        timeout : 100000,
        success : function(response) {
         },
        error : function(e) {
        alert("ERROR: ", e);
        }
        });
    });
});