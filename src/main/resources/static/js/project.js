$(document).ready(function () {
    $("#submit_project_form").click(function () {
        $.ajax({
        type : "POST",
        contentType: "application/json; charset=utf-8",
        url :"https://to-do-app21.herokuapp.com/rest/project/add",
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

     $("#submit_task_form").click(function () {
            $.ajax({
            type : "POST",
            contentType: "application/json; charset=utf-8",
            url :"https://to-do-app21.herokuapp.com/rest/task/add",
            data: JSON.stringify({
                title: $("#task_title").val(),
                description: $("#task_description").val(),
                projectName: $("#project_name").val()
            }),
            timeout : 100000,
            success : function(response) {
            },
            error : function(e) {
            }
            });
        });
});
