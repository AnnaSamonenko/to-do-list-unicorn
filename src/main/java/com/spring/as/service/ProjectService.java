package com.spring.as.service;

import com.spring.as.model.Project;

import java.util.List;

public interface ProjectService {

    void createProject(Project project);

    void deleteProject(Long id);

    List<Project> getProjects();

    Project findProjectByProjectName(String projectName);

    boolean isProjectPresent(String projectName);
}
