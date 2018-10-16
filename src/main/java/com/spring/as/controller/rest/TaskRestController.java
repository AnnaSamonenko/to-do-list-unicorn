package com.spring.as.controller.rest;

import com.spring.as.entity.TaskDTO;
import com.spring.as.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/task")
public class TaskRestController {

    @Autowired
    TaskService taskService;

    @GetMapping("/all")
    List<TaskDTO> getAll() {
        return taskService.getAllTasks();
    }

    @PostMapping("/add")
    void create(@RequestParam("title") String title, @RequestParam("description") String description) {
        taskService.createTask(new TaskDTO(title, description));
    }

}