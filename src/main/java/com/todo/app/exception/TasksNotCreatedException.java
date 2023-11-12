package com.todo.app.exception;

public class TasksNotCreatedException extends RuntimeException{

    public TasksNotCreatedException(String message) {
        super(message);
    }
}
