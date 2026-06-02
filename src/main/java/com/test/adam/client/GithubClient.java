package com.test.adam.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.test.adam.dto.github.GithubBranchDto;
import com.test.adam.dto.github.GithubRepositoryDto;
import com.test.adam.exception.UserNotFoundException;

@Component
public class GithubClient {
    private final RestClient restClient;

    public GithubClient(RestClient githubRestClient) {
        this.restClient = githubRestClient;
    }

    public List<GithubRepositoryDto> getRepositories(String username) {
        try {
            GithubRepositoryDto[] repositories = restClient.get()
                    .uri("/users/{username}/repos", username)
                    .retrieve()
                    .body(GithubRepositoryDto[].class);

            return repositories == null
                    ? Collections.emptyList()
                    : Arrays.asList(repositories);

        } catch (HttpClientErrorException.NotFound ex) {
            throw new UserNotFoundException(username);
        } catch (HttpClientErrorException ex) {
            String msg = String.format("Error fetching repositories for user: %s --- %s",username,ex.getMessage());
            throw new RuntimeException(msg, ex);
        }
    }

    public List<GithubBranchDto> getBranches(String owner, String repository) {
        try {
            GithubBranchDto[] branches = restClient.get()
                    .uri("/repos/{owner}/{repo}/branches", owner, repository)
                    .retrieve()
                    .body(GithubBranchDto[].class);

            return branches == null
                    ? Collections.emptyList()
                    : Arrays.asList(branches);
        } catch (HttpClientErrorException ex) {
            throw new RuntimeException("Error fetching branches for repository: " + repository, ex);
        }
    }
}
