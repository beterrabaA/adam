package com.test.adam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.adam.client.GithubClient;
import com.test.adam.dto.github.GithubBranchDto;
import com.test.adam.dto.github.GithubRepositoryDto;
import com.test.adam.dto.response.BranchResponse;
import com.test.adam.dto.response.RepositoryResponse;
import com.test.adam.interfaces.RepositoryService;

@Service
public class RepositoryServiceImpl implements RepositoryService {

        private final GithubClient githubClient;

        public RepositoryServiceImpl(GithubClient githubClient) {
                this.githubClient = githubClient;
        }

        @Override
        public List<RepositoryResponse> getRepositories(String username) {

                List<GithubRepositoryDto> repositories = githubClient.getRepositories(username);

                return repositories.stream()
                                .filter(repository -> !repository.fork())
                                .map(this::toRepositoryResponse)
                                .toList();
        }

        private RepositoryResponse toRepositoryResponse(
                        GithubRepositoryDto repository) {

                List<GithubBranchDto> branches = githubClient.getBranches(
                                repository.owner().login(),
                                repository.name());

                List<BranchResponse> branchResponses = branches.stream()
                                .map(branch -> new BranchResponse(
                                                branch.name(),
                                                branch.commit().sha()))
                                .toList();

                return new RepositoryResponse(
                                repository.name(),
                                repository.owner().login(),
                                branchResponses);
        }
}
