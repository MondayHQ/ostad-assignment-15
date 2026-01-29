package com.example.securednote.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.Instant;
import java.util.NoSuchElementException;

// Local Imports
import com.example.securednote.exceptions.dto.CustomErrorResponse;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {

        String message = "Record exists";

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CustomErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        message,
                        Instant.now().toEpochMilli()
                ));

    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleAuthenticationException(UsernameNotFoundException e) {
        String message = "Unauthorized";

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new CustomErrorResponse(
                        HttpStatus.UNAUTHORIZED.value(),
                        message,
                        Instant.now().toEpochMilli()
                ));
    }

    // API request with bad parameters
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        String message = e.getBindingResult().getFieldErrors().getFirst().getDefaultMessage();

        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                message,
                Instant.now().toEpochMilli()    // UTC
        );

        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);

    }

    // Catch-all
    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<CustomErrorResponse> handleNotFoundException(NoSuchElementException ex) {

        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Unexpected error",
                Instant.now().toEpochMilli()    // UTC
        );

        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

}
