$(document).ready(function () {

    // render all tasks
    $.ajax({
        url: "http://localhost:8080/api/task/all"
    }).then(function (data) {
        var task_data = '';
        $.each(data, function (key, value) {
            task_data += '<tr><td>' + value.title + '<div class="date">' + value.date + '</div></td>';
            task_data += '<td>' + value.deadline + '</td>';
            task_data += '<td>' + value.status + '</td>';
            task_data += '<td><input type="image" src="img/done.png" class="ADDCLASSHERE" width="25" height="25">';
            task_data += '<input type="image" src="img/update.png" class="ADDCLASSHERE" width="25" height="25"> </td></tr>';
        });
        $('table.tasks').append(task_data);

    });

    // render all projects
    $.ajax({
        url: "http://localhost:8080/api/project/all"
    }).then(function (data) {
        var project_data = '';
        $.each(data, function (key, value) {
            project_data += '<tr>';
            project_data += '<td>' + value.name + '</td>';
            project_data += '<td><input type="image" id="' + value.id +'" src="img/trash.png" class="remove_project" width="25" height="25"></td>';
            project_data += '</tr>';
        });
        $('table.projects').append(project_data);

        $('.remove_project').click(function (event) {
            $.ajax({
                type: "DELETE",
                url: "http://localhost:8080/api/project/" + event.target.id,
                timeout: 100000,
                success: function () {
                    location.reload();
                },
                error: function () {
                }
            });
        })
    });

    // add project
    $("input.add_project").click(function(){
        var project_data = '<tr class="to_add">';
        project_data += '<td> <input type="text" name="title" id="project_name"> </td>';
        project_data += '<td> <input type="image" src="img/close.svg" class="close_project_form" width="20" height="20">';
        project_data += '<input type="image" src="img/done.png" class="send_project_form" width="25" height="25"> </td>';

        $('.projects').append(project_data);
        $('input.add_project').attr('disabled', true);

        $("input.close_project_form").click(function () {
            $('table.projects tr:last').remove();
            $('input.add_project').attr('disabled', false);
        });

        $(".send_project_form").click(function () {
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "http://localhost:8080/api/project/add",
                data: JSON.stringify({
                    name: $("#project_name").val()
                }),
                timeout: 100000,
                success: function () {
                    location.reload();
                },
                error: function (e) {
                }
            });
        });
    });

    // add task
    $("input.add_task").click(function () {
        var task_data = '<tr class="to_add">';
        task_data += '<td><input type="text" name="title" id="task_title"> </td>';
        task_data += '<td><input type="date" id="deadline" name="deadline" value="2019-12-12"/></td>';
        task_data += '<td></td>';
        task_data += '<td><input type="image" src="img/close.svg" class="close_task_form" width="20" height="20">';
        task_data += '<input type="image" src="img/done.png" class="send_task_form" width="25" height="25"></td></tr>';

        $('table.tasks').append(task_data);
        $('input.add_task').attr('disabled', true);

        $("input.close_task_form").click(function () {
            $('table.tasks tr:last').remove();
            $('input.add_task').attr('disabled', false);
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
                success: function () {
                    location.reload();
                },
                error: function (e) {
                }
            });
        });
    });

});
