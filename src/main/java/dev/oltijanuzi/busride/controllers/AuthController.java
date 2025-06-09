package dev.oltijanuzi.busride.controllers;

import dev.oltijanuzi.busride.dtos.AuthRequest;
import dev.oltijanuzi.busride.dtos.AuthResponse;
import dev.oltijanuzi.busride.dtos.RegisterRequest;
import dev.oltijanuzi.busride.entities.User;
import dev.oltijanuzi.busride.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        AuthResponse authResponse = authService.register(registerRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    // Login existing user
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = authService.login(authRequest);
        if (authResponse == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(authResponse);
    }
}
