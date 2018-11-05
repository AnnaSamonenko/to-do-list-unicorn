$(document).ready(function () {
    //print all data from db
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

    //
    $("button.add_project").click(function () {
        $(".new_project_form").show();
    });

    $(".close_project_form").click(function () {
        $(".new_project_form").hide();
    });

});



