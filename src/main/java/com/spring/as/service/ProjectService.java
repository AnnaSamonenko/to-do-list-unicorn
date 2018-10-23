package com.spring.as.service;

import com.spring.as.dao.ProjectDAOImpl;
import com.spring.as.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectDAOImpl projectDAO;

    public void createProject(Project project) {
        projectDAO.create(project);
    }

}
