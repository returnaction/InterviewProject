package nikita.taskservice.mapper;

import nikita.taskservice.dto.TaskCreateDto;
import nikita.taskservice.dto.TaskResponseDto;
import nikita.taskservice.model.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    // не работает
    TaskEntity toEntity(TaskCreateDto dto);
    TaskResponseDto toDto(TaskEntity taskEntity);
}