package com.gmail.samonenko.service;

import com.gmail.samonenko.repository.ProjectDAO;
import com.gmail.samonenko.model.Project;
import com.gmail.samonenko.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@PropertySource("classpath:validation.properties")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private Environment env;

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Project createProject(Project project) {
        User user = userService.getAuthorizedUser();

        if (isProjectPresent(project.getName()))
            throw new IllegalArgumentException(env.getProperty("project.already_present"));

        project.setUser(user);
        projectDAO.create(project);
        List<Project> projects = user.getProjects();
        projects.add(project);
        user.setProjects(projects);
        userService.update(user);
        return project;
    }

    @Override
    public void deleteProject(Long id) {
        if (projectDAO.read(id) == null)
            throw new IllegalArgumentException(env.getProperty("project.not_found"));
        projectDAO.delete(id);
    }

    @Override
    public List<Project> getProjects() {
        return userService.getAuthorizedUser().getProjects();
    }

    @Override
    public Project findProjectByProjectName(String projectName) {
        User user = userService.getAuthorizedUser();
        List<Project> projects = projectDAO.read(user);
        for (Project pr : projects) {
            if (pr.getName().equals(projectName)) {
                return pr;
            }
        }
        throw new IllegalArgumentException(env.getProperty("project.not_found"));
    }

    @Override
    public Project createDefaultProject() {
        return createProject(Project.builder().name("Default").build());
    }

    @Override
    public boolean isDefaultProjectPresent() {
        return isProjectPresent("Default");
    }

    @Override
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
