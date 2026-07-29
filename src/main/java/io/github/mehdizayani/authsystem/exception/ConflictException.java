package io.github.mehdizayani.authsystem.exception;

/**
 * Exception thrown when a requested operation conflicts with
 * the current state of the resource.
 * <p>
 * Typically results in an HTTP 409 (Conflict) response.
 */
public class ConflictException extends RuntimeException {
    /**
     * Creates a new  conflict exception.
     *
     * @param message error message returned to the client
     */
    public ConflictException(String message) {
        super(message);
    }
}