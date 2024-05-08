package com.todonest.api.service;

import com.todonest.api.entity.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectService {

    void createProject(Project project);

    List<Project> getProjects();

    Project getProjectById(Long id);

    List<Project> getProjectsByUser(Long userId);

    boolean updateProject(Project project);

    boolean deleteProject(long id);
}
