package com.spring.as.service;

import com.spring.as.dto.TaskDTO;
import com.spring.as.model.Task;

import java.util.List;

public interface TaskService {

    Task createTask(TaskDTO taskDTO);

    Task getTask(long id);

    List<Task> getAllTasks();

    void deleteTask(long id);

}
