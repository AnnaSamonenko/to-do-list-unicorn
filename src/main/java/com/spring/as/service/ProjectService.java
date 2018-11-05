package com.spring.as.service;

import com.spring.as.dao.ProjectDAOImpl;
import com.spring.as.model.Project;
import com.spring.as.model.User;
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

        if (isProjectPresent(project.getName()))
            throw new IllegalArgumentException("Project with such name is already present");

        project.setUser(user);
        projectDAO.create(project);
        List<Project> projects = user.getProjects();
        projects.add(project);
        user.setProjects(projects);
        userService.update(user);
    }

    public void deleteProject(long id) {
        if (projectDAO.read(id) == null)
            throw new IllegalArgumentException("There is no project with id: " + id);
        projectDAO.delete(id);
    }

    public List<Project> getProjects() {
        return userService.getAuthorizedUser().getProjects();
    }

    public Project findProjectByProjectName(String projectName) {
        User user = userService.getAuthorizedUser();
        List<Project> projects = projectDAO.read(user);
        for (Project pr : projects) {
            if (pr.getName().equals(projectName)) {
                return pr;
            }
        }
        throw new IllegalArgumentException("You don't have such project");
    }

    public boolean isProjectPresent(String projectName) {
        User user = userService.getAuthorizedUser();
        List<Project> projects = projectDAO.read(user);
        for (Project pr : projects) {
            if (pr.getName().equals(projectName)) {
                return true;
            }
        }
        return false;
    }

}
