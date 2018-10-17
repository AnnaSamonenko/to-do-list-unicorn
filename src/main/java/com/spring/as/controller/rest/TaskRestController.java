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
    void create(@RequestBody TaskDTO taskDTO) {
        taskDTO.setDate(LocalDate.now());
        taskService.createTask(taskDTO);
    }

    @GetMapping("/{id}")
    TaskDTO get(@PathVariable("id") long id) {
        return taskService.getTask(id);
    }

}