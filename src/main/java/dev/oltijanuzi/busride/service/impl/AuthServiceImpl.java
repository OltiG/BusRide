package dev.oltijanuzi.busride.service.impl;

import dev.oltijanuzi.busride.dtos.AuthRequest;
import dev.oltijanuzi.busride.dtos.AuthResponse;
import dev.oltijanuzi.busride.entity.User;
import dev.oltijanuzi.busride.repository.UserRepository;
import dev.oltijanuzi.busride.security.JwtService;
import dev.oltijanuzi.busride.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(AuthRequest request) {
        log.info("Starting registration process for email: {}", request.getEmail());
        try {
            // Validate request data
            if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
                log.error("Registration failed: Email is null or empty");
                throw new RuntimeException("Email is required");
            }
            if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
                log.error("Registration failed: Password is null or empty");
                throw new RuntimeException("Password is required");
            }
            if (request.getFirstName() == null || request.getFirstName().trim().isEmpty()) {
                log.error("Registration failed: First name is null or empty");
                throw new RuntimeException("First name is required");
            }
            if (request.getLastName() == null || request.getLastName().trim().isEmpty()) {
                log.error("Registration failed: Last name is null or empty");
                throw new RuntimeException("Last name is required");
            }

            log.info("Checking if email already exists: {}", request.getEmail());
            if (userRepository.existsByEmail(request.getEmail())) {
                log.error("Registration failed: Email already exists: {}", request.getEmail());
                throw new RuntimeException("Email already exists");
            }

            log.info("Creating new user with email: {}", request.getEmail());
            var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(request.getEmail().endsWith("@admin.com") ? "ADMIN" : "USER")
                .createdAt(LocalDateTime.now())
                .build();
            
            log.info("Saving user to database: {}", request.getEmail());
            userRepository.save(user);
            log.info("User saved successfully: {}", request.getEmail());
            
            log.info("Generating JWT token for user: {}", request.getEmail());
            var token = jwtService.generateToken(user);
            
            log.info("Registration completed successfully for user: {}", request.getEmail());
            return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
        } catch (Exception e) {
            log.error("Registration failed for email: {}. Error: {}", request.getEmail(), e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        log.info("Attempting login for email: {}", request.getEmail());
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
                )
            );
            
            var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
            
            log.info("User found: {}", user.getEmail());
            
            var token = jwtService.generateToken(user);
            
            return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
        } catch (Exception e) {
            log.error("Login failed for email: {}", request.getEmail(), e);
            throw e;
        }
    }
} 