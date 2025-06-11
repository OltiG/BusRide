package dev.oltijanuzi.busride.services;

import dev.oltijanuzi.busride.dtos.AuthRequest;
import dev.oltijanuzi.busride.dtos.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);
    AuthResponse register(AuthRequest request);
} 