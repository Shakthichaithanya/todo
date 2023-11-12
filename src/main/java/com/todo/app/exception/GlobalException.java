package com.todo.app.exception;

import com.todo.app.info.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;


@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(TasksNotCreatedException.class)
    public ResponseEntity<ErrorInfo> TaskNotCreatedExceptionHandler(TasksNotCreatedException exception, WebRequest request) {
        ErrorInfo error = new ErrorInfo(HttpStatus.NOT_FOUND, LocalDate.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> customConstraintViolationExceptionHandler(MethodArgumentNotValidException exception, WebRequest request) {
        ErrorInfo error = new ErrorInfo(HttpStatus.BAD_REQUEST, LocalDate.now(), exception.getBindingResult().getFieldError().getDefaultMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception, WebRequest request) {
        ErrorInfo error = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, LocalDate.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
