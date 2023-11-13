package com.todo.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
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
