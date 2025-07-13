package nikita.taskservice.controller;

import lombok.RequiredArgsConstructor;
import nikita.taskservice.dto.TaskCreateDto;
import nikita.taskservice.dto.TaskResponseDto;
import nikita.taskservice.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDto> create(@RequestBody TaskCreateDto taskDto) {
        TaskResponseDto task = taskService.createTask(taskDto);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getById(@PathVariable Long id) {
        TaskResponseDto task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllByUserId(@RequestParam Long userId) {
        List<TaskResponseDto> allTasksByUserId = taskService.getAllTasksByUserId(userId);
        return new ResponseEntity<>(allTasksByUserId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> update(@PathVariable Long id, @RequestBody TaskCreateDto taskDto) {
        TaskResponseDto task = taskService.updateTask(id, taskDto);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

    //TODO 📌 Позже, когда добавим JWT-фильтр на api-gateway, userId будет извлекаться из токена, и контроллер будет выглядеть так:
//    @GetMapping
//    public List<TaskDto> getMyTasks(@AuthenticationPrincipal JwtPrincipal principal) {
//        Long userId = principal.getUserId(); // например, или через header
//        return service.getAllByUserId(userId);
//    }

