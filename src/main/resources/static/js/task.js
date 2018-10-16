$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/rest/task/all"
    }).then(function (data) {
        var task_data = '';
        $.each(data, function (key, value) {
            task_data += '<tr>';
            task_data += '<td>' + value.title + '</td>';
            task_data += '<td>' + value.description + '</td>';
            task_data += '<td>' + value.date + '</td>';
            task_data += '</tr>';
        });
        $('.task_table').append(task_data);
    });
});
