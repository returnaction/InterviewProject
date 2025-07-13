package nikita.userservice.mapper;

import nikita.userservice.model.Role; // 👈 вот это обязательно!
import nikita.userservice.model.User;
import nikita.userservice.model.UserDto.UserRequestDto;
import nikita.userservice.model.UserDto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", expression = "java(user.getRole().name())")
    UserResponseDto toDto(User user);

    @Mapping(target = "role", expression = "java(dto.getRole() == null ? nikita.userservice.model.Role.USER : nikita.userservice.model.Role.valueOf(dto.getRole()))")
    @Mapping(target = "id", ignore = true) // если id генерируется базой
    User toEntity(UserRequestDto dto);
}
