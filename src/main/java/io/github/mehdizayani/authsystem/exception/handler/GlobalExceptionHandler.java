package io.github.mehdizayani.authsystem.exception.handler;

import io.github.mehdizayani.authsystem.exception.BadRequestException;
import io.github.mehdizayani.authsystem.exception.ConflictException;
import io.github.mehdizayani.authsystem.exception.ForbiddenException;
import io.github.mehdizayani.authsystem.exception.InternalServerException;
import io.github.mehdizayani.authsystem.exception.ResourceNotFoundException;
import io.github.mehdizayani.authsystem.exception.UnauthorizedException;
import io.github.mehdizayani.authsystem.exception.response.ApiErrorResponse;
import io.github.mehdizayani.authsystem.exception.response.ValidationError;
import io.github.mehdizayani.authsystem.exception.response.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAuthorizationDenied(
            AuthorizationDeniedException ex,
            HttpServletRequest request
    ) {

        ApiErrorResponse error = ApiErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.FORBIDDEN.value())
                .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                .message("Access denied")
                .path(request.getRequestURI())
                .build();

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(
            ResourceNotFoundException exception,
            HttpServletRequest request
    ) {

        log.warn("Resource not found: {}", exception.getMessage());

        return buildResponse(
                HttpStatus.NOT_FOUND,
                "RESOURCE_NOT_FOUND",
                "Resource not found.",
                request
        );
    }


    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiErrorResponse> handleConflict(
            ConflictException exception,
            HttpServletRequest request
    ) {

        log.warn("Conflict: {}", exception.getMessage());

        return buildResponse(
                HttpStatus.CONFLICT,
                "CONFLICT",
                exception.getMessage(),
                request
        );
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequest(
            BadRequestException exception,
            HttpServletRequest request
    ) {

        log.warn("Bad request: {}", exception.getMessage());

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "BAD_REQUEST",
                exception.getMessage(),
                request
        );
    }


    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiErrorResponse> handleUnauthorized(
            UnauthorizedException exception,
            HttpServletRequest request
    ) {

        log.warn("Unauthorized: {}", exception.getMessage());

        return buildResponse(
                HttpStatus.UNAUTHORIZED,
                "UNAUTHORIZED",
                exception.getMessage(),
                request
        );
    }


    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiErrorResponse> handleForbidden(
            ForbiddenException exception,
            HttpServletRequest request
    ) {

        log.warn("Forbidden: {}", exception.getMessage());

        return buildResponse(
                HttpStatus.FORBIDDEN,
                "FORBIDDEN",
                exception.getMessage(),
                request
        );
    }


    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ApiErrorResponse> handleInternalServer(
            InternalServerException exception,
            HttpServletRequest request
    ) {

        log.error("Internal server error", exception);

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred.",
                request
        );
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleUnknownException(
            Exception exception,
            HttpServletRequest request
    ) {

        log.error("Unexpected error", exception);

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred.",
                request
        );
    }


    private ResponseEntity<ApiErrorResponse> buildResponse(
            HttpStatus status,
            String code,
            String message,
            HttpServletRequest request
    ) {

        ApiErrorResponse response = ApiErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .code(code)
                .error(status.getReasonPhrase())
                .message(message)
                .path(request.getRequestURI())
                .traceId(UUID.randomUUID().toString())
                .build();

        return ResponseEntity.status(status).body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {

        log.warn("Validation failed: {}", exception.getMessage());

        List<ValidationError> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ValidationError(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        ValidationErrorResponse response = ValidationErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .code("VALIDATION_FAILED")
                .error("Bad Request")
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