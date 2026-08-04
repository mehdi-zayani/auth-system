package io.github.mehdizayani.authsystem.exception;

/**
 * Exception thrown when an authenticated user does not have
 * permission to perform the requested operation.
 * <p>
 * Typically results in an HTTP 403 (Forbidden) response.
 */
public class ForbiddenException extends RuntimeException {
    /**
     * Creates a new forbidden exception.
     *
     * @param message error message returned to the client
     */
    public ForbiddenException(String message) {
        super(message);
    }
}