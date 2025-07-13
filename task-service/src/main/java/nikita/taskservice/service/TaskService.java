package nikita.taskservice.service;

import nikita.taskservice.dto.TaskCreateDto;
import nikita.taskservice.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {
    TaskResponseDto createTask(TaskCreateDto taskDto);

    TaskResponseDto getTaskById(Long id);

    List<TaskResponseDto> getAllTasksByUserId(Long userId);

    TaskResponseDto updateTask(Long id, TaskCreateDto taskDto);

    void deleteTask(Long id);
}
