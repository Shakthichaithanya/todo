package com.todo.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    @NotBlank(message = "task name must be present")
    private String taskName;
    @NotBlank(message = "task description must be present")
    private String description;
    private LocalDateTime taskCreateDate;
    private LocalDateTime taskCompletionDate;
    private String taskStatus;
    @NotBlank(message = "user name must be present")
    private String taskCreatedUserName;
}
