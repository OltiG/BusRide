package dev.oltijanuzi.busride.controllers;

import dev.oltijanuzi.busride.entity.User;
import dev.oltijanuzi.busride.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            
            log.info("Fetching profile for user: {}", email);
            
            User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
            
            Map<String, Object> profile = new HashMap<>();
            profile.put("email", user.getEmail());
            profile.put("firstName", user.getFirstName());
            profile.put("lastName", user.getLastName());
            profile.put("role", user.getRole());
            
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            log.error("Error fetching profile: {}", e.getMessage(), e);
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to fetch profile");
            return ResponseEntity.badRequest().body(error);
        }
    }
} 