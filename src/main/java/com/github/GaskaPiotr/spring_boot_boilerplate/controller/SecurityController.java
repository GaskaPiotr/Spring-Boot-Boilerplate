package com.github.GaskaPiotr.spring_boot_boilerplate.controller;

import com.github.GaskaPiotr.spring_boot_boilerplate.dto.LoginRequest;
import com.github.GaskaPiotr.spring_boot_boilerplate.dto.LoginResponse;
import com.github.GaskaPiotr.spring_boot_boilerplate.service.SecurityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class SecurityController {
    private final SecurityService securityService;

    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = securityService.login(request);
        return ResponseEntity.ok(response);
    }
}
