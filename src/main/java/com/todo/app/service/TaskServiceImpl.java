package com.todo.app.service;

import com.todo.app.dto.TaskDTO;
import com.todo.app.entity.Task;
import com.todo.app.exception.TasksNotCreatedException;
import com.todo.app.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TaskDTO> getAllTasks() {
        List<TaskDTO> taskList = taskRepository.findAll().stream().map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
        if (taskList.isEmpty()) {
            throw new TasksNotCreatedException("Tasks are not yet createed");
        }
        return taskList;
    }

    @Override
    public String addTask(TaskDTO task) {
        Task newTask = modelMapper.map(task, Task.class);
        newTask.setTaskCreateDate(LocalDateTime.now());
        newTask.setTaskCompletionDate(LocalDateTime.now().plus(24, ChronoUnit.HOURS));
        taskRepository.save(newTask);
        String message = "new task added";
        return message;

    }

    @Override
    public String deleteTask(Long id) {
        taskRepository.deleteById(id);
        String message = "Task deleted successfully";
        return message;
    }
}
