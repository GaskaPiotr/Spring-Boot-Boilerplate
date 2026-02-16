package com.github.GaskaPiotr.spring_boot_boilerplate.dto;

import jakarta.validation.constraints.Email;

public record RegisterRequest(
        @Email
        String email,

        @StrongPassword
        String password
) {}
