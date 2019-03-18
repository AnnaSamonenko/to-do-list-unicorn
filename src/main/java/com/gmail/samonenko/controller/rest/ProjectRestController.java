package com.gmail.samonenko.controller.rest;

import com.gmail.samonenko.dto.ProjectDTO;
import com.gmail.samonenko.model.Project;
import com.gmail.samonenko.service.ProjectServiceImpl;
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
@RequestMapping("/api/project")
public class ProjectRestController {

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private ModelMapper mapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity create(@RequestBody @Valid ProjectDTO project, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ErrorDetails(HttpStatus.BAD_REQUEST.toString(), bindingResult),
                    HttpStatus.BAD_REQUEST);

        Project projectEntity = mapper.map(project, Project.class);
        projectService.createProject(projectEntity);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Project> getProjectsOfUser() {
        return projectService.getProjects();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
