package com.todonest.api.controller;

import com.todonest.api.entity.Project;
import com.todonest.api.service.IProjectService;
import com.todonest.api.serviceImpl.GitHubService;
import com.todonest.api.utils.MarkdownGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/gist",produces = {"application/json"})
@CrossOrigin(origins = "*") // Allow requests from any origin
public class GistController {

    @Autowired
    private GitHubService gitHubService;

    @Autowired
    private IProjectService iProjectService;

    @PostMapping("/")
    public String createGist(@RequestBody Project project) {
        String projectTitle = project.getTitle();
        String projectContent = MarkdownGenerator.toMarkdown(project);
//        System.out.println(gitHubService.createSecretGist(projectTitle, projectContent));
        return gitHubService.createSecretGist(projectTitle, projectContent);
    }

    @GetMapping("/markdown/{projectId}")
    public ResponseEntity<String> getMarkdownContent(@PathVariable Long projectId) {
        try {
            Project project = iProjectService.getProjectById(projectId);
            String markdownContent = MarkdownGenerator.toMarkdown(project);
            return ResponseEntity.ok().contentType(MediaType.TEXT_MARKDOWN).body(markdownContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching markdown content");
        }
    }
}
