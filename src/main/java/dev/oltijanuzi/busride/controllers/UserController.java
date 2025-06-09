package dev.oltijanuzi.busride.controllers;

import dev.oltijanuzi.busride.dtos.UserDto;
import dev.oltijanuzi.busride.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // GET /api/users - get all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // GET /api/users/{id} - get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
    }

    // POST /api/users - create user
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto created = userService.saveUser(userDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // PUT /api/users/{id} - update user
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        UserDto existing = userService.getUserById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        userDto.setId(id);
        UserDto updated = userService.saveUser(userDto);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/users/{id} - delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        UserDto existing = userService.getUserById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
