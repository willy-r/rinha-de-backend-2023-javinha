package com.api.rinhadebackend.controllers.exceptions;

import com.api.rinhadebackend.services.exceptions.NotFoundException;
import com.api.rinhadebackend.services.exceptions.UnprocessableEntityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<StandardError> handleUnprocessableEntityException(UnprocessableEntityException exc, HttpServletRequest req) {
        return sendResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY, exc.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exc, HttpServletRequest req) {
        return sendResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY, exc.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> handleNotFoundException(NotFoundException exc, HttpServletRequest req) {
        return sendResponseEntity(HttpStatus.NOT_FOUND, exc.getMessage(), req.getRequestURI());
    }

    private ResponseEntity<StandardError> sendResponseEntity(HttpStatus status, String errorMessage, String reqURI) {
        var standardError = new StandardError(
            LocalDateTime.now(),
            status.value(),
            status.getReasonPhrase(),
            errorMessage,
            reqURI
        );
        return ResponseEntity.status(status).body(standardError);
    }
}