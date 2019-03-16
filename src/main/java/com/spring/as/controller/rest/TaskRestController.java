package com.spring.as.controller.rest;

import com.spring.as.dto.TaskDTO;
import com.spring.as.model.Project;
import com.spring.as.model.Task;
import com.spring.as.service.ProjectService;
import com.spring.as.service.TaskServiceImpl;
import com.spring.as.service.UserService;
import com.spring.as.validation.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/task")
public class TaskRestController {

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    List<Task> getAll() {
        return taskService.getAllTasks();
    }

    @PostMapping("/add")
    ResponseEntity create(@RequestBody @Valid TaskDTO taskDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ErrorDetails(HttpStatus.BAD_REQUEST.toString(), bindingResult),
                    HttpStatus.BAD_REQUEST);

        if (taskDTO.getProjectName() == null) {
            // add task to default project
            if(!projectService.isDefaultProjectPresent()){
                Project project = projectService.createDefaultProject();
                taskDTO.setProjectName(project.getName());
            }
            taskDTO.setProjectName("Default");
        }
        taskService.createTask(taskDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    Task get(@PathVariable("id") long id) {
        return taskService.getTask(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        taskService.deleteTask(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}