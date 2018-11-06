$(document).ready(function () {
   $('.message a').click(function(){
      $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
   });

     $(".registration").click(function () {
           $.ajax({
               type: "POST",
               contentType: "application/json; charset=utf-8",
               url: "http://localhost:8080/api/user/registration",
               data: JSON.stringify({
                   username: $("#username").val(),
                   email: $("#email").val(),
                   password: $("#password").val()
               }),
               timeout: 100000,
               success: function (response) {
               },
               error: function (e) {
               }
           });
       });

       $(".login").click(function () {
                  $.ajax({
                      type: "POST",
                      contentType: "application/json; charset=utf-8",
                      url: "http://localhost:8080/login",
                      data: JSON.stringify({
                          username: $("#uname").val(),
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

