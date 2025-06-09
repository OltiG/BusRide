package dev.oltijanuzi.busride.services;

import dev.oltijanuzi.busride.dtos.AuthRequest;
import dev.oltijanuzi.busride.dtos.AuthResponse;
import dev.oltijanuzi.busride.dtos.RegisterRequest;

public interface AuthService {
    /**
     * Registers a new user based on the RegisterRequest data.
     * Returns an AuthResponse containing JWT token and expiry on success.
     *
     * @param registerRequest registration data including username, email, password, role
     * @return AuthResponse with JWT token and expiry date
     */
    AuthResponse register(RegisterRequest registerRequest);

    /**
     * Authenticates a user based on the AuthRequest data.
     * Returns AuthResponse with JWT if authentication is successful, or null if failed.
     *
     * @param authRequest login credentials including email and password
     * @return AuthResponse with JWT token and expiry date, or null if authentication fails
     */
    AuthResponse login(AuthRequest authRequest);
}
