package dev.oltijanuzi.busride.mappers;

import dev.oltijanuzi.busride.dtos.UserDto;
import dev.oltijanuzi.busride.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
