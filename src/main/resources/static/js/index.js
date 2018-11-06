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

     $.ajax({
            url: "http://localhost:8080/api/task/all"
        }).then(function (data) {
            var task_data = '';
            $.each(data, function (key, value) {
                task_data += '<tr>';
                task_data += '<td>' + value.title + '</td>';
                task_data += '<td>' + value.description + '</td>';
                task_data += '<td>' + value.date + '</td>';
                task_data += '<td>' + value.deadline + '</td>';
                task_data += '</tr>';
            });
            $('.table').append(task_data);
        });

        $("button.add_task").click(function () {
            $(".new_task_form").show();
        });

        $(".close_task_form").click(function () {
            $(".new_task_form").hide();
        });

        $("button.add_project").click(function () {
            $(".new_project_form").show();
        });

        $(".close_project_form").click(function () {
            $(".new_project_form").hide();
        });
});
