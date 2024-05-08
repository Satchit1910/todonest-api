package com.todonest.api.controller;

import com.todonest.api.constants.ProjectConstants;
import com.todonest.api.dto.ResponseDto;
import com.todonest.api.entity.Project;
import com.todonest.api.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/project",produces = {"application/json"})
@CrossOrigin(origins = "*") // Allow requests from any origin
public class ProjectController {

    @Autowired
    private IProjectService iProjectService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createProject(@RequestBody Project project) {
        iProjectService.createProject(project);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ProjectConstants.STATUS_201,ProjectConstants.MESSAGE_201));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Project>> fetchAllProjects() {
        List<Project> allProjects = iProjectService.getProjects();
        return ResponseEntity.status(HttpStatus.OK).body(allProjects);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Project> fetchProject(@PathVariable long id) {
        Project project = iProjectService.getProjectById(id);
        return ResponseEntity.status(HttpStatus.OK).body(project);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Project>> getProjectsByUserId(@PathVariable Long userId) {
        List<Project> projects = iProjectService.getProjectsByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateProject(@RequestBody Project project) {
        boolean isUpdated = iProjectService.updateProject(project);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ProjectConstants.STATUS_200,ProjectConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(ProjectConstants.STATUS_417,ProjectConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteProject(@PathVariable Long id) {
        boolean isDeleted = iProjectService.deleteProject(id);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ProjectConstants.STATUS_200,ProjectConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(ProjectConstants.STATUS_417,ProjectConstants.MESSAGE_417_DELETE));
        }
    }
}
