package com.todonest.api.serviceImpl;

import com.todonest.api.entity.Project;
import com.todonest.api.entity.User;
import com.todonest.api.exception.ResourceNotFoundException;
import com.todonest.api.repository.ProjectRepository;
import com.todonest.api.repository.UserRepository;
import com.todonest.api.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createProject(Project project) {
        project.setCreatedAt(LocalDateTime.now());
        Project newProject = projectRepository.save(project);
    }

    @Override
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Project","project Id",Long.toString(id))
        );
    }

    @Override
    public List<Project> getProjectsByUser(Long userId) {
        User connectedUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","Id",Long.toString(userId))
        );
        return projectRepository.findByUser(connectedUser);
    }

    @Override
    public boolean updateProject(Project project) {
        boolean isUpdated = false;
        if(project!=null) {
            Project oldProject = projectRepository.findById(project.getId()).orElseThrow(
                    () -> new ResourceNotFoundException("Project","Id",Long.toString(project.getId()))
            );
            oldProject.setTitle(project.getTitle());
            Project updatedProject = projectRepository.save(oldProject);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteProject(long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Project","Id",Long.toString(id))
        );
        projectRepository.deleteById(id);
        return true;
    }
}
