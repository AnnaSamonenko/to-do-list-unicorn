package com.spring.as.controller.rest;

import com.spring.as.entity.TaskDTO;
import com.spring.as.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/task")
public class TaskRestController {

    @Autowired
    TaskService taskService;

    @GetMapping("/all")
    List<TaskDTO> home() {
        return taskService.getAllTasks();
    }

}