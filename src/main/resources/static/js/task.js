$(document).ready(function () {
    //print all data from db
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

    // add data to db
    $("button.add_task").click(function () {
        var form = $("<form></form>");
        form.append('Title: <input type="text" name="title"><br>' +
            'Description: <input type="text" name="description"><br>' +
            '<input type="button" value="close">' +
            '<input type="submit">');
        $("table").after().append(form);
    });
});



