package com.spring.as.service;

import com.spring.as.dto.AddTaskDTO;
import com.spring.as.model.Task;

import java.util.List;

public interface TaskService {

    Task createTask(AddTaskDTO taskDTO);

    Task getTask(long id);

    List<Task> getAllTasks();

    void deleteTask(long id);

}
