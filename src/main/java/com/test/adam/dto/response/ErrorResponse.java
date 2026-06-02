package com.test.adam.dto.response;

public record ErrorResponse(
        int status,
        String message
) {}
