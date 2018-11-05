$(document).ready(function () {
    $("#submit_project_form").click(function () {
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8080/api/project/add",
            data: JSON.stringify({
                name: $("#title").val()
            }),
            timeout: 100000,
            success: function (response) {
            },
            error: function (e) {
            }
        });
    });

    $("#submit_task_form").click(function () {
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8080/api/task/add",
            data: JSON.stringify({
                title: $("#task_title").val(),
                description: $("#task_description").val(),
                projectName: $("#project_name").val(),
                deadline: $("#deadline").val()
            }),
            timeout: 100000,
            success: function (response) {
            },
            error: function (e) {
            }
        });
    });
});
