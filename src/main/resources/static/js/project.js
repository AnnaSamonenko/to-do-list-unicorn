$(document).ready(function () {
    $("#submit_project_form").click(function () {
        $.ajax({
        type : "POST",
        contentType: "application/json; charset=utf-8",
        url :"http://localhost:8080/rest/project/add",
        data: JSON.stringify({
            name: $("#title").val()
        }),
        timeout : 100000,
        success : function(response) {
        },
        error : function(e) {
        }
        });
    });
});

//$(document).ready(function () {
//    $("").click(function () {
//        $.ajax({
//        type : "POST",
//        contentType: "application/json; charset=utf-8",
//        url :"http://localhost:8080/rest/project/add",
//        data: JSON.stringify({
//            project_name: $("#title").val()
//        }),
//        timeout : 100000,
//        success : function(response) {
//        },
//        error : function(e) {
//        }
//        });
//    });
//});