package io.github.mehdizayani.authsystem.exception.handler;

import io.github.mehdizayani.authsystem.exception.response.ValidationError;
import io.github.mehdizayani.authsystem.exception.response.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Handles request validation exceptions and converts them into
 * standardized validation error responses.
 * <p>
 * Validation errors include all invalid request fields and their
 * corresponding validation messages.
 */
@Slf4j
@RestControllerAdvice
public class ValidationExceptionHandler {


    /**
     * Handles request validation failures.
     *
     * @param exception the validation exception
     * @param request the current HTTP request
     * @return a standardized validation error response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {

        log.warn("Validation failed: {}", exception.getMessage());

        List<ValidationError> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> ValidationError.builder()
                        .field(error.getField())
                        .message(error.getDefaultMessage())
                        .build())
                .toList();


        ValidationErrorResponse response = ValidationErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .code("VALIDATION_FAILED")
                .error("Validation Failed")
                .message("Request validation failed.")
                .path(request.getRequestURI())
                .traceId(UUID.randomUUID().toString())
                .errors(errors)
                .build();


        return ResponseEntity
                .badRequest()
                .body(response);
    }
}