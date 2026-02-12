package com.github.GaskaPiotr.spring_boot_boilerplate.dto;

public record ErrorResponse (
        int status,
        String message
) {}
