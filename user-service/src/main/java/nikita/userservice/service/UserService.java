package nikita.userservice.service;

import lombok.RequiredArgsConstructor;
import nikita.userservice.kafka.event.UserCreatedEvent;
import nikita.userservice.kafka.producer.UserEventProducer;
import nikita.userservice.mapper.UserMapper;
import nikita.userservice.model.User;
import nikita.userservice.model.UserDto.UserRequestDto;
import nikita.userservice.model.UserDto.UserResponseDto;
import nikita.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserEventProducer userEventProducer;

    public UserResponseDto createUser(UserRequestDto request){
        // 1. Преобразуем DTO → Entity
        User entity = userMapper.toEntity(request);
        // 2. Сохраняем пользователя в БД
        User saved = userRepository.save(entity);
        // 3. Отправляем событие в Kafka
        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(saved.getId())
                .name(saved.getUsername())
                .role(saved.getRole())
                .build();

        userEventProducer.sendUserCreatedEvent(event);
        // 4. Возвращаем обратно DTO
        return userMapper.toDto(saved);
    }

}
