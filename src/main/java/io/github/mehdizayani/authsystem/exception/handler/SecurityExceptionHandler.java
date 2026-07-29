package io.github.mehdizayani.authsystem.exception.handler;

import io.github.mehdizayani.authsystem.exception.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.UUID;

/**
 * Handles Spring Security exceptions and converts them into
 * standardized API error responses.
 * <p>
 * This handler is responsible for authentication and authorization
 * failures raised by Spring Security.
 */
@Slf4j
@RestControllerAdvice
public class SecurityExceptionHandler {

    /**
     * Handles authentication failures.
     *
     * @param exception the authentication exception
     * @param request the current HTTP request
     * @return a standardized 401 Unauthorized response
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiErrorResponse> handleAuthentication(
            AuthenticationException exception,
            HttpServletRequest request
    ) {

        log.warn("Authentication failed: {}", exception.getMessage());

        return buildResponse(
                HttpStatus.UNAUTHORIZED,
                "INVALID_CREDENTIALS",
                "Authentication failed.",
                request
        );
    }

    /**
     * Handles authorization failures when an authenticated user
     * attempts to access a protected resource without sufficient privileges.
     *
     * @param exception the access denied exception
     * @param request the current HTTP request
     * @return a standardized 403 Forbidden response
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDenied(
            AccessDeniedException exception,
            HttpServletRequest request
    ) {

        log.warn("Access denied: {}", exception.getMessage());

        return buildResponse(
                HttpStatus.FORBIDDEN,
                "ACCESS_DENIED",
                "You do not have permission to access this resource.",
                request
        );
    }

    /**
     * Builds a standardized API error response.
     *
     * @param status the HTTP status
     * @param code the application-specific error code
     * @param message the error message
     * @param request the current HTTP request
     * @return the response entity containing the error payload
     */
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
}