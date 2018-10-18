$(document).ready(function () {
    $("input.register").click(function () {
        $.ajax({
        type : "POST",
        contentType: "application/json; charset=utf-8",
        url :"http://localhost:8080/rest/user/add",
        data: JSON.stringify({
            login: $("input.login").val(),
            email: $("input.email").val(),
            password: $("input.password").val()
        }),
        timeout : 100000,
        success : function(response) {
        alert(response);
         },
        error : function(e) {
        alert("ERROR: ", e);
        }
        });
    });
});