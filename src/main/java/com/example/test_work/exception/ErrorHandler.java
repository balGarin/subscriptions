package com.example.test_work.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestControllerAdvice(value = "com.example.test_work")
public class ErrorHandler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(value = {ValidationException.class,
            MethodArgumentNotValidException.class,
            NumberFormatException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidate(Throwable exception) {
        log.warn("Произошло исключение : {}", exception.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST, "Incorrectly made request.",
                exception.getMessage(), LocalDateTime.now().format(formatter));
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(NotFoundException exception) {
        log.warn("Произошло исключение : {}", exception.getMessage());
        return new ErrorResponse(HttpStatus.NOT_FOUND, "Object had not found",
                exception.getMessage(), LocalDateTime.now().format(formatter));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIncorrectDateException(IncorrectDataException exception) {
        log.warn("Произошло исключение : {}", exception.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST, "Incorrectly made request.",
                exception.getMessage(), LocalDateTime.now().format(formatter));
    }

    @ExceptionHandler(value = {ConstraintViolationException.class, DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse constraintViolationExceptionHandle(Throwable exception) {
        log.warn("Произошло исключение : {}", exception.getMessage());
        return new ErrorResponse(HttpStatus.CONFLICT, "Integrity constraint has been violated.",
                exception.getMessage(), LocalDateTime.now().format(formatter));
    }
}


