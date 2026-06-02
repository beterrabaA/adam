package com.test.adam.dto.response;

import java.util.List;

public record RepositoryResponse(
        String name,
        String owner,
        List<BranchResponse> branches
) {}
