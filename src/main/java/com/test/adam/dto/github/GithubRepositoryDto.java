package com.test.adam.dto.github;

public record GithubRepositoryDto(
        String name,
        boolean fork,
        GithubOwnerDto owner
) {}
