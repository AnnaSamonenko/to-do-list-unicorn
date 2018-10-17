$(document).ready(function () {
 $("input.register").click(function () {

    var loginValue = $("input.login").val();
    var emailValue = $("input.email").val();
    var passwordValue = $("input.password").val();
    $.ajax({
    type : "POST",
    url :"http://localhost:8080/rest/user/add",
    data : {login:loginValue, email: emailValue, password: passwordValue},
    timeout : 100000,
    success : function(response) {
    alert(response);
     },
    error : function(e) {
    console.log("ERROR: ", e);
    });
    }
 });
});
});
