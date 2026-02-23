package com.github.GaskaPiotr.spring_boot_boilerplate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ErrorResponse (
        int status,
        String message,
        Instant timestamp,
        Map<String, String> errors
) {
    public ErrorResponse(int status, String message) {
        this(status, message, Instant.now(), null);
    }

    public ErrorResponse(int status, String message, Map<String, String> errors) {
        this(status, message, Instant.now(), errors);
    }
}
