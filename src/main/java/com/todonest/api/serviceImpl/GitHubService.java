package com.todonest.api.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubService {
    @Value("${github.pat}")
    private String githubPat;

    public String createSecretGist(String projectTitle, String projectContent) {
        String url = "https://api.github.com/gists";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + githubPat);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = getStringHttpEntity(projectTitle, projectContent, headers);
        ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return null;
        }
    }

    private static HttpEntity<String> getStringHttpEntity(String projectTitle, String projectContent, HttpHeaders headers) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode requestBody = objectMapper.createObjectNode();
        requestBody.put("description", projectTitle);
        requestBody.put("public", false);

        ObjectNode filesNode = objectMapper.createObjectNode();
        ObjectNode fileContentNode = objectMapper.createObjectNode();
        fileContentNode.put("content", projectContent);
        filesNode.set(projectTitle + ".md", fileContentNode);

        requestBody.set("files", filesNode);

        // Convert JSON object to string
        String requestBodyString = requestBody.toString();

        HttpEntity<String> entity = new HttpEntity<>(requestBodyString, headers);
        return entity;
    }
}
