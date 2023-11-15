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

    /**
     * Creating a new task
     * @param task
     * @return ResponseInfo
     */
    @PostMapping()
    public ResponseEntity<ResponseInfo> addTask(@Valid @RequestBody TaskDTO task) {
        String message = taskService.addTask(task);
        ResponseInfo response = new ResponseInfo(HttpStatus.CREATED,message);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    /**
     * getting all tasks
     * @return List<TAskDTO>
     */
    @GetMapping()
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    /**
     * Deleting task using id
     * @param id
     * @return ResponseInfo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseInfo> deleteTask(@PathVariable("id") Long id) {
        String message = taskService.deleteTask(id);
        ResponseInfo response = new ResponseInfo(HttpStatus.OK,message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
