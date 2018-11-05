package com.spring.as.service;

import com.spring.as.dao.TaskDAOImpl;
import com.spring.as.dto.AddTaskDTO;
import com.spring.as.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskDAOImpl taskDAO;

    @Autowired
    private ProjectService projectService;

    public List<Task> getAllTasks() {
        return taskDAO.getAll();
    }

    public Task createTask(AddTaskDTO taskDTO) {
        if (!projectService.isProjectPresent(taskDTO.getProjectName()))
            throw new IllegalArgumentException("Project with such name is not present");
        Task task = new Task();
        task.setDate(LocalDate.now());
        task.setDeadline(taskDTO.getDeadline());
        task.setDescription(taskDTO.getDescription());
        task.setTitle(taskDTO.getTitle());
        task.setProject(projectService.findProjectByProjectName(taskDTO.getProjectName()));
        taskDAO.create(task);
        return task;
    }

    public Task getTask(long id) {
        if (taskDAO.read(id) == null)
            throw new IllegalArgumentException("There no task with id" + id);
        return taskDAO.read(id);
    }

}
