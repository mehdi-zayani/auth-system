package io.github.mehdizayani.authsystem.exception;

/**
 * Exception thrown when authentication is required or has failed.
 * <p>
 * Typically results in an HTTP 401 (Unauthorized) response.
 */
public class UnauthorizedException extends RuntimeException {

    /**
     * Creates a new unauthorized exception.
     *
     * @param message error message
     */
    public UnauthorizedException(String message) {
        super(message);
    }
}