package com.spring.as.controller.rest;

import com.spring.as.dto.TaskDTO;
import com.spring.as.entity.Task;
import com.spring.as.entity.User;
import com.spring.as.service.ProjectService;
import com.spring.as.service.TaskService;
import com.spring.as.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/task")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/all")
    List<Task> getAll() {
        return taskService.getAllTasks();
    }

    @PostMapping("/add")
    void create(@RequestBody TaskDTO taskDTO) {
        Task task = new Task();
        User user = userService.getAuthorizedUser();
        task.setDate(LocalDate.now());
        task.setDescription(taskDTO.getDescription());
        task.setTitle(taskDTO.getTitle());

        task.setProject(projectService.findProjectByProjectName(user, taskDTO.getProjectName()));
        taskService.createTask(task);
    }

    @GetMapping("/{id}")
    Task get(@PathVariable("id") long id) {
        return taskService.getTask(id);
    }

}