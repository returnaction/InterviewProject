package nikita.taskservice.service;

import nikita.taskservice.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto);

    TaskDto getTaskById(Long id);

    List<TaskDto> getAllTasksByUserId(Long userId);

    TaskDto updateTask(Long id, TaskDto taskDto);

    void deleteTask(Long id);
}
