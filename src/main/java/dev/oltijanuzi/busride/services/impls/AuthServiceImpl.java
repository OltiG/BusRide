package dev.oltijanuzi.busride.services.impls;

import dev.oltijanuzi.busride.dtos.AuthRequest;
import dev.oltijanuzi.busride.dtos.AuthResponse;
import dev.oltijanuzi.busride.dtos.RegisterRequest;
import dev.oltijanuzi.busride.entities.Role;
import dev.oltijanuzi.busride.entities.User;
import dev.oltijanuzi.busride.repositories.UserRepo;
import dev.oltijanuzi.busride.services.AuthService;
import dev.oltijanuzi.busride.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        // Check if user email already exists
        Optional<User> existingUser = userRepo.findByEmail(registerRequest.getEmail());
        if (existingUser.isPresent()) {
            // Could throw custom exception here; for simplicity return null or handle properly in controller
            return null;
        }

        // Create new User entity
        User newUser = User.builder()
                .username(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole() != null ? registerRequest.getRole() : Role.USER)
                .build();

        userRepo.save(newUser);

        // Generate JWT token
        String token = jwtService.generateToken(newUser.getUsername(), Map.of(
                "role", newUser.getRole().name()
        ));
        long expiresAt = System.currentTimeMillis() + 86400000; // 1 day in ms; consider config property

        return new AuthResponse(token, expiresAt);
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        Optional<User> userOpt = userRepo.findByUsername(authRequest.getEmail());
        if (userOpt.isEmpty()) {
            return null;
        }

        User user = userOpt.get();

        // Verify password
        if (!bCryptPasswordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            return null;
        }

        // Generate JWT token
        String token = jwtService.generateToken(user.getUsername(), Map.of(
                "role", user.getRole().name()
        ));
        long expiresAt = System.currentTimeMillis() + 86400000; // 1 day in ms; consider config property

        return new AuthResponse(token, expiresAt);
    }
}
