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

    public void createProject(Project project) {
        projectDAO.create(project);
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
