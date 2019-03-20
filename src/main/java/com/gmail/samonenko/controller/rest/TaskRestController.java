package com.gmail.samonenko.controller.rest;

import com.gmail.samonenko.dto.TaskDTO;
import com.gmail.samonenko.model.Project;
import com.gmail.samonenko.model.Status;
import com.gmail.samonenko.model.Task;
import com.gmail.samonenko.service.ProjectService;
import com.gmail.samonenko.service.TaskServiceImpl;
import com.gmail.samonenko.validation.ErrorDetails;
import org.modelmapper.ModelMapper;
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
    private ModelMapper mapper;

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
            if (!projectService.isDefaultProjectPresent()) {
                Project project = projectService.createDefaultProject();
                taskDTO.setProjectName(project.getName());
            }
            taskDTO.setProjectName("Default");
        }
        Task task = mapper.map(taskDTO, Task.class);
        task.setProject(projectService.findProjectByProjectName(taskDTO.getProjectName()));
        task.setStatus(Status.NEW);
        taskService.createTask(task);
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