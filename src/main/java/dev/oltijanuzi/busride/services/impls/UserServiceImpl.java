package dev.oltijanuzi.busride.services.impls;

import dev.oltijanuzi.busride.dtos.UserDto;
import dev.oltijanuzi.busride.entities.User;
import dev.oltijanuzi.busride.mappers.UserMapper;
import dev.oltijanuzi.busride.repositories.UserRepo;
import dev.oltijanuzi.busride.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;


    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepo.save(user));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepo.findById(id)
                .map(userMapper::toDto)
                .orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
