package com.spring.as.controller.rest;

import com.spring.as.entity.Project;
import com.spring.as.entity.User;
import com.spring.as.service.ProjectService;
import com.spring.as.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/project")
public class ProjectRestController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody()
    public void createProject(@RequestBody Project project) {
        User user = userService.getAuthorizedUser();
        project.setUser(user);
        projectService.createProject(project);
        List<Project> projects = user.getProjects();
        projects.add(project);
        user.setProjects(projects);
        userService.update(user);
    }

    @GetMapping("/all")
    public List<Project> getProjectsOfUser() {
        return userService.getAuthorizedUser().getProjects();
    }

}
