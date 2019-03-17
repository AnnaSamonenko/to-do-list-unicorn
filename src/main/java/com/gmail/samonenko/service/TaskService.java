package com.gmail.samonenko.service;

import com.gmail.samonenko.model.Task;

import java.util.List;

public interface TaskService {

    Task createTask(Task taskDTO);

    Task getTask(long id);

    List<Task> getAllTasks();

    void deleteTask(long id);

}
