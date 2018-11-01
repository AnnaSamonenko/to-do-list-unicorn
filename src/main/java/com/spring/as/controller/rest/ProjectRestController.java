package com.spring.as.controller.rest;

import com.spring.as.entity.Project;
import com.spring.as.service.ProjectService;
import com.spring.as.validation.AddProjectValidationForm;
import com.spring.as.validation.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/project")
public class ProjectRestController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AddProjectValidationForm addProjectValidationForm;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> create(@RequestBody Project project, BindingResult bindingResult) {
        addProjectValidationForm.validate(project, bindingResult);

        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ErrorDetails(HttpStatus.BAD_REQUEST.toString(), bindingResult),
                    HttpStatus.BAD_REQUEST);

        projectService.createProject(project);

        return new ResponseEntity(project, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Project> getProjectsOfUser() {
        return projectService.getProjects();
    }

}
