package com.test.adam.dto.github;

public record GithubBranchDto(
        String name,
        GithubCommitDto commit
) {}
