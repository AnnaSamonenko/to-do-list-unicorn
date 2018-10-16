package com.spring.as.service;

import com.spring.as.dao.TaskDAO;
import com.spring.as.entity.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskDAO taskDAO;

    public List<TaskDTO> getAllTasks() {
        return taskDAO.getAllTasks();
    }
}
