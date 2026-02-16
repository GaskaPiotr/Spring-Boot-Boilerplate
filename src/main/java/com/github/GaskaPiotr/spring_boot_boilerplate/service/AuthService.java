package com.github.GaskaPiotr.spring_boot_boilerplate.service;

import com.github.GaskaPiotr.spring_boot_boilerplate.dto.LoginRequest;
import com.github.GaskaPiotr.spring_boot_boilerplate.dto.LoginResponse;
import com.github.GaskaPiotr.spring_boot_boilerplate.entity.User;
import com.github.GaskaPiotr.spring_boot_boilerplate.repository.UserRepository;
import com.github.GaskaPiotr.spring_boot_boilerplate.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;
    JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
        ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String jwtToken = jwtService.generateToken(user);
        return new LoginResponse(jwtToken);
    }
    
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            // Throw exception
        }
        
        User user = new User();
        
        // TODO set user parameters
        
        
        // TODO return some register response
        return RegisterResponse()
    }
}
