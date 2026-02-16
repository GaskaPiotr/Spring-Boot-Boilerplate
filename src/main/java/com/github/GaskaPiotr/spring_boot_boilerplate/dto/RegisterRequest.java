package com.github.GaskaPiotr.spring_boot_boilerplate.dto;

public record RegisterRequest(
        String email,
        String password
) {}
