package com.spring.as.service;

import com.spring.as.model.Project;
import com.spring.as.model.User;

import java.util.List;

public interface ProjectService {

    boolean isDefaultProjectPresent();

    Project createDefaultProject();

    Project createProject(Project project);

    void deleteProject(Long id);

    List<Project> getProjects();

    Project findProjectByProjectName(String projectName);

    boolean isProjectPresent(String projectName);
}
