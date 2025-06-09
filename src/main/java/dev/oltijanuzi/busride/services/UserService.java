package dev.oltijanuzi.busride.services;

import dev.oltijanuzi.busride.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    void deleteUser(Long id);
}
