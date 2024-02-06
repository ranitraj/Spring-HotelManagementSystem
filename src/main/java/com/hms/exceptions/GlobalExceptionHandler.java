package com.hms.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundExceptions across the whole application.
     * @param exception The caught ResourceNotFoundException.
     * @return A ResponseEntity with a 404 Not Found status and the exception message.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
        log.error("Resource Not Found exception occurred: ", exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    /**
     * Handles exceptions triggered by validation failures.
     * This method is invoked when an object fails validation checks before being processed by a controller method.
     * It extracts and formats validation error messages, mapping them to their respective field names.
     *
     * @param exception The MethodArgumentNotValidException containing details about the validation errors.
     * @return A ResponseEntity containing a map of field names to validation error messages and the HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        log.error("Validation exception occurred: ", exception);

        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /**
     * Handles all uncaught exceptions across the application.
     * Logs the exception details and returns a generic error response with a 500 Internal Server Error status.
     * This method catches any exception not specifically handled by other @ExceptionHandler methods.
     *
     * @param exception the caught exception
     * @return a ResponseEntity containing the error message and HTTP status code
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
        log.error("Unhandled exception occurred: ", exception);
        return new ResponseEntity<>(
                "An error occurred: " + exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
