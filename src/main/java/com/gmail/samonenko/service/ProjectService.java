package com.gmail.samonenko.service;

import com.gmail.samonenko.model.Project;

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
