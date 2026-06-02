package com.test.adam.interfaces;

import java.util.List;

import com.test.adam.dto.response.RepositoryResponse;

public interface RepositoryService {
    List<RepositoryResponse> getRepositories(String username);
}