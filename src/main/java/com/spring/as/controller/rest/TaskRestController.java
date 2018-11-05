package com.spring.as.controller.rest;

import com.spring.as.dto.AddTaskDTO;
import com.spring.as.model.Task;
import com.spring.as.service.TaskService;
import com.spring.as.validation.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/task")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/all")
    List<Task> getAll() {
        return taskService.getAllTasks();
    }

    @PostMapping("/add")
    ResponseEntity create(@RequestBody @Valid AddTaskDTO taskDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ErrorDetails(HttpStatus.BAD_REQUEST.toString(), bindingResult),
                    HttpStatus.BAD_REQUEST);
        taskService.createTask(taskDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    Task get(@PathVariable("id") long id) {
        return taskService.getTask(id);
    }

}