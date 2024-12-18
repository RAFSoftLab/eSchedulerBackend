package com.eScheduler.exceptions;

import com.eScheduler.exceptions.custom.ConflictException;
import com.eScheduler.exceptions.custom.NotFoundException;
import com.eScheduler.exceptions.custom.ServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Map<String, String>> handleConflictException(ConflictException e) {
        logger.warn("conflictException: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409
                .body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException e) {
        logger.warn("notFoundException: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // 404
                .body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<Map<String, String>> handleServerErrorException(ServerErrorException e) {
        logger.warn("serverErrorException: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                .body(Map.of("message", e.getMessage()));
    }
}
