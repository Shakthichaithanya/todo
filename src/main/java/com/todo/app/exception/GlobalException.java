package com.todo.app.exception;

import com.todo.app.info.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;


@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(TasksNotCreatedException.class)
    public ResponseEntity<ErrorInfo> TaskNotCreatedException(TasksNotCreatedException exception, WebRequest request) {

        ErrorInfo error = new ErrorInfo(HttpStatus.NOT_FOUND,LocalDate.now(),exception.getMessage(),request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }
}
