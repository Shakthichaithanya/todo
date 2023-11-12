package com.todo.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private long id;
    private String taskName;
    private String description;
    private Date taskCreateDate;
    private Date taskCompletionDate;
    private String taskStatus;
    private String taskCreatedUserName;
}
