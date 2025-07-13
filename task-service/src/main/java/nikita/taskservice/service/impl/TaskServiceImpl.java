package nikita.taskservice.service.impl;

import lombok.RequiredArgsConstructor;
import nikita.taskservice.dto.TaskCreateDto;
import nikita.taskservice.dto.TaskResponseDto;
import nikita.taskservice.mapper.TaskMapper;
import nikita.taskservice.model.TaskEntity;
import nikita.taskservice.repository.TaskRepository;
import nikita.taskservice.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskResponseDto createTask(TaskCreateDto taskDto) {
        TaskEntity entity = new TaskEntity();
        entity.setTitle(taskDto.getTitle());
        entity.setDescription(taskDto.getDescription());
        entity.setDueDate(taskDto.getDueDate());
        entity.setStatus(taskDto.getStatus());
        entity.setUserId(taskDto.getUserId());

        TaskEntity saved = taskRepository.save(entity);

        TaskResponseDto dto = new TaskResponseDto();
        dto.setId(saved.getId());
        dto.setTitle(saved.getTitle());
        dto.setDescription(saved.getDescription());
        dto.setCreatedAt(saved.getCreatedAt());
        dto.setDueDate(saved.getDueDate());
        dto.setStatus(saved.getStatus());
        dto.setUserId(saved.getUserId());

        return dto;
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {
        TaskEntity entity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("\tTask with id " + id + " not found"));

        TaskResponseDto dto = new TaskResponseDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setDueDate(entity.getDueDate());
        dto.setStatus(entity.getStatus());
        dto.setUserId(entity.getUserId());

        return dto;
    }

    @Override
    public List<TaskResponseDto> getAllTasksByUserId(Long userId) {
        return taskRepository.findAllByUserId(userId).stream()
                .map(entity -> {
                    TaskResponseDto dto = new TaskResponseDto();
                    dto.setId(entity.getId());
                    dto.setTitle(entity.getTitle());
                    dto.setDescription(entity.getDescription());
                    dto.setCreatedAt(entity.getCreatedAt());
                    dto.setDueDate(entity.getDueDate());
                    dto.setStatus(entity.getStatus());
                    dto.setUserId(entity.getUserId());
                    return dto;
                })
                .toList();
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskCreateDto taskDto) {
        TaskEntity existingEntity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("\tTask with id " + id + " not found"));

        existingEntity.setTitle(taskDto.getTitle());
        existingEntity.setDescription(taskDto.getDescription());
        existingEntity.setDueDate(taskDto.getDueDate());
        existingEntity.setStatus(taskDto.getStatus());

        TaskEntity saved = taskRepository.save(existingEntity);

        TaskResponseDto dto = new TaskResponseDto();
        dto.setId(saved.getId());
        dto.setTitle(saved.getTitle());
        dto.setDescription(saved.getDescription());
        dto.setCreatedAt(saved.getCreatedAt());
        dto.setDueDate(saved.getDueDate());
        dto.setStatus(saved.getStatus());
        dto.setUserId(saved.getUserId());

        return dto;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}

