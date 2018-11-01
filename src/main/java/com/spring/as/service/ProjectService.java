package com.spring.as.service;

import com.spring.as.dao.ProjectDAOImpl;
import com.spring.as.entity.Project;
import com.spring.as.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectDAOImpl projectDAO;

    @Autowired
    private UserService userService;

    public void createProject(Project project) {
        User user = userService.getAuthorizedUser();
        project.setUser(user);
        projectDAO.create(project);
        List<Project> projects = user.getProjects();
        projects.add(project);
        user.setProjects(projects);
        userService.update(user);
    }

    public List<Project> getProjects() {
        return userService.getAuthorizedUser().getProjects();
    }

    public Project findProjectByProjectName(User user, String projectName) {
        List<Project> projects = projectDAO.read(user);
        for (Project pr : projects) {
            if (pr.getName().equals(projectName)) {
                return pr;
            }
        }
        throw new IllegalArgumentException("You don't have such project");
    }

}
