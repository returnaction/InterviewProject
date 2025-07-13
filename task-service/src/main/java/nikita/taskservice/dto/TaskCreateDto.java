package nikita.taskservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nikita.taskservice.model.TaskStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskCreateDto {
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private TaskStatus status;
    private Long userId;
}