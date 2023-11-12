package com.todo.app.service;

import com.todo.app.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getAllTasks();

    String addTask(TaskDTO task);
}
