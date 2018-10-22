package com.spring.as.service;

import com.spring.as.dao.TaskDAOImpl;
import com.spring.as.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskDAOImpl taskDAO;

    public List<Task> getAllTasks() {
        return taskDAO.getAll();
    }

    public void createTask(Task taskDTO) {
        taskDAO.create(taskDTO);
    }

    public Task getTask(long id) {
        return taskDAO.read(id);
    }

}
