package com.github.GaskaPiotr.spring_boot_boilerplate.controller;

import com.github.GaskaPiotr.spring_boot_boilerplate.dto.LoginRequest;
import com.github.GaskaPiotr.spring_boot_boilerplate.dto.LoginResponse;
import com.github.GaskaPiotr.spring_boot_boilerplate.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    AuthService authService;

    @InjectMocks
    AuthController authController;

    @Test
    void login_UserLogsIn_GetsTokenAndHttpOk() {

        // Arrange
        String email = "user@test.com";
        String password = "testpassword";

        LoginRequest request = new LoginRequest(email, password);

        LoginResponse response = new LoginResponse("test-token");

        when(authService.login(request)).thenReturn(response);


        // Act
        ResponseEntity<LoginResponse> result = authController.login(request);

        // Assert

        // 1. Check the status code
        assertEquals(HttpStatus.OK, result.getStatusCode());

        // 2. Check the body
        LoginResponse body = result.getBody();

        assertEquals(response, body);

        assertNotNull(body, "Response body should not be null");

        assertEquals("test-token", body.token());

        // 3. Check the delegation to service
        verify(authService).login(request);


    }

}