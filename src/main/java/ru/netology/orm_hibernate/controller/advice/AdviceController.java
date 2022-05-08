package ru.netology.orm_hibernate.controller.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.orm_hibernate.exception.DbEntityExistException;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(DbEntityExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage entityProblemException(DbEntityExistException e) {
        System.err.println(e.getClass() + " -> " + e.getEntityProblem() + ": " + e.getNameSurnameAge());
        return new ErrorMessage(e.getEntityProblem(), e.getNameSurnameAge());
    }

    @Data
    @AllArgsConstructor
    private static class ErrorMessage {
        private String message;
        private Object entity;
    }
}
