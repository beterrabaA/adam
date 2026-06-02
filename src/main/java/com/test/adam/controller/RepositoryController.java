package com.test.adam.controller;

import org.springframework.web.bind.annotation.RestController;

import com.test.adam.dto.response.RepositoryResponse;
import com.test.adam.service.RepositoryServiceImpl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("api/v1/repositories")
public class RepositoryController {
    private final RepositoryServiceImpl repositoryService;

    public RepositoryController(RepositoryServiceImpl repositoryService) {
        this.repositoryService = repositoryService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<RepositoryResponse>> getRepositoriesByUsername(
            @PathVariable("username") String username) {
        List<RepositoryResponse> repositories = this.repositoryService.getRepositories(username);
        return ResponseEntity.ok(repositories);
    }
}