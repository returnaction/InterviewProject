package nikita.userservice.mapper;

import nikita.userservice.model.User;
import nikita.userservice.model.UserDto.UserRequestDto;
import nikita.userservice.model.UserDto.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // 💡 componentModel = "spring" — чтобы Spring сам регистрировал бин.
public interface UserMapper {
    User toEntity(UserRequestDto dto);
    UserResponseDto toDto(User entity);
}
