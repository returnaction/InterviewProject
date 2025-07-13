package nikita.taskservice.service.impl;

import lombok.RequiredArgsConstructor;
import nikita.taskservice.dto.TaskDto;
import nikita.taskservice.mapper.TaskMapper;
import nikita.taskservice.model.TaskEntity;
import nikita.taskservice.repository.TaskRepository;
import nikita.taskservice.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        TaskEntity entity = taskMapper.toEntity(taskDto);
        return taskMapper.toDto(taskRepository.save(entity));
    }

    @Override
    public TaskDto getTaskById(Long id){
        TaskEntity entity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("\tTask with " + id + " not found"));
        TaskDto dto = taskMapper.toDto(entity);
        return dto;
    }

    @Override
    public List<TaskDto> getAllTasksByUserId(@RequestParam Long userid){
        List<TaskEntity> listEntityByUserId = taskRepository.findAllByUserId(userid);
        List<TaskDto> listDtoByUserId = listEntityByUserId.stream()
                .map(taskMapper::toDto)
                .toList();

        return listDtoByUserId;
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        TaskEntity existingEntity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("\tTask with id" + id + "not found"));

        existingEntity.setTitle(taskDto.getTitle());
        existingEntity.setDescription(taskDto.getDescription());
        existingEntity.setDueDate(taskDto.getDueDate());
        existingEntity.setStatus(taskDto.getStatus());

        TaskEntity saved = taskRepository.save(existingEntity);

        TaskDto dto = taskMapper.toDto(saved);
        return dto;
    }

    @Override
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
