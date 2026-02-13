package com.github.GaskaPiotr.spring_boot_boilerplate.service;

import com.github.GaskaPiotr.spring_boot_boilerplate.dto.LoginRequest;
import com.github.GaskaPiotr.spring_boot_boilerplate.entity.User;
import com.github.GaskaPiotr.spring_boot_boilerplate.repository.UserRepository;
import com.github.GaskaPiotr.spring_boot_boilerplate.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    JwtService jwtService;

    @InjectMocks
    AuthService authService;

    @Test
    void login_UserExistsAndPassCorrect_AuthenticatesSuccessfully() {

        // Arrange
        String email = "user@example.com";
        String password = "123456";
        LoginRequest request = new LoginRequest(email, password);

        User testUser = new User(email, password);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(testUser));

        when(jwtService.generateToken(testUser)).thenReturn("fake-jwt-token");

        // Act
        authService.login(request);

        // Assert

        ArgumentCaptor<UsernamePasswordAuthenticationToken> captor = ArgumentCaptor.forClass(UsernamePasswordAuthenticationToken.class);
        verify(authenticationManager).authenticate(captor.capture());

        assertEquals(email, captor.getValue().getPrincipal());
        assertEquals(password, captor.getValue().getCredentials());

        verify(userRepository).findByEmail(email);
    }

    @Test
    void login_WrongPassword_ThrowException() {

        // Arrange

        String email = "good@email.com";
        String password = "wrongpassword";

        LoginRequest request = new LoginRequest(email, password);

        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        // Act & Assert

        // 1. Check if login throws an exception
        assertThrows(BadCredentialsException.class, () -> authService.login(request));

        // 2. Check if database was ever touched
        verify(userRepository, never()).findByEmail(any());
    }
}