package com.spring.as.controller.rest;

import com.spring.as.entity.Project;
import com.spring.as.service.ProjectService;
import com.spring.as.validation.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/project")
public class ProjectRestController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity create(@RequestBody @Valid Project project, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ErrorDetails(HttpStatus.BAD_REQUEST.toString(), bindingResult),
                    HttpStatus.BAD_REQUEST);

        projectService.createProject(project);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Project> getProjectsOfUser() {
        return projectService.getProjects();
    }

}
