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

    // render all tasks
    $.ajax({
        url: "http://localhost:8080/api/task/all"
    }).then(function (data) {
        var task_data = '';
        $.each(data, function (key, value) {
            task_data += '<tr>';
            task_data += '<td>' + value.title + '</td>';
            task_data += '<td>' + value.date + '</td>';
            task_data += '<td>' + value.date + '</td>';
            task_data += '<td>' + value.deadline + '</td>';
            task_data += '<td> <input type="image" src="img/img2.png" class="ADDCLASSHERE" width="25" height="25"> </td>';
            task_data += '</tr>';
        });
        $('table.tasks').append(task_data);
    });

    // render all projects
    $.ajax({
        url: "http://localhost:8080/api/project/all"
    }).then(function (data) {
        var task_data = '';
        $.each(data, function (key, value) {
            task_data += '<tr>';
            task_data += '<td>' + value.name + '</td>';
            task_data += '</tr>';
        });
        $('table.projects_table').append(task_data);
    });

    $(".add_project").click(function(){
        var project_data = '';
        project_data += '<tr class="to_add">';
        project_data += '<td> <input type="text" name="title" id="project_name"> </td>';
        project_data += '<td> <input type="image" src="img/img1.svg" class="close_project_form" width="20" height="20">';
        project_data += '<input type="image" src="img/img2.png" class="send_project_form" width="25" height="25"> </td>';

        $('table.projects').append(project_data);
    });

    // render form for add task
    $(".add_task").click(function () {
        var task_data = '';
        task_data += '<tr class="to_add">';
        task_data += '<td> <input type="text" name="title" id="task_title"> </td>';
        task_data += '<td>  </td>';
        task_data += '<td> <input type="date" id="deadline" name="deadline" value="2019-12-12"/> </td>';
        task_data += '<td> <input type="image" src="img/img1.svg" class="close_task_form" width="20" height="20">';
        task_data += '<input type="image" src="img/img2.png" class="send_task_form" width="25" height="25"> </td>';
        task_data += '</tr>';
        $('table.tasks').append(task_data);

        $("input.close_task_form").click(function () {
            $('table tr:last').remove();
        });

        $("input.send_task_form").click(function () {
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "http://localhost:8080/api/task/add",
                data: JSON.stringify({
                    title: $("#task_title").val(),
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

    $("button.add_project").click(function () {
        $(".new_project_form").show();
    });

    $(".close_project_form").click(function () {
        $(".new_project_form").hide();
    });
});
