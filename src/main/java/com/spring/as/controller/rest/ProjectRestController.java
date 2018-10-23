package com.spring.as.controller.rest;

import com.spring.as.entity.Project;
import com.spring.as.entity.User;
import com.spring.as.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@RequestMapping("/rest/project")
public class ProjectRestController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void createProject(@RequestBody Project project) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
    }

}
