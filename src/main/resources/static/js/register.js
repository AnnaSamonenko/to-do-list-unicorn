$(document).ready(function () {
    $("input.register").click(function () {
        var loginValue = $("input.login").val();
        var emailValue = $("input.email").val();
        var passwordValue = $("input.password").val();
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
        var data =
        alert("ERROR: ", e);
        alert($("input.login").val() + " " + $("input.email").val() + " "+ $("input.password").val());
        }
        });
    });
});