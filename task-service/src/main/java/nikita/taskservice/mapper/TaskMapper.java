package nikita.taskservice.mapper;

import nikita.taskservice.dto.TaskDto;
import nikita.taskservice.model.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(TaskEntity taskEntity);
    TaskEntity toEntity(TaskDto dto);
}
