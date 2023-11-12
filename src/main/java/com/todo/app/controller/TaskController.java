package com.todo.app.controller;

import com.todo.app.dto.TaskDTO;
import com.todo.app.info.ResponseInfo;
import com.todo.app.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity<ResponseInfo> addTask(@Valid @RequestBody TaskDTO task) {
        String message = taskService.addTask(task);
        ResponseInfo response = new ResponseInfo(HttpStatus.CREATED,message);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
    @GetMapping()
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }
}
