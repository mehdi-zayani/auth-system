package io.github.mehdizayani.authsystem.exception;

/**
 * Exception thrown when a requested resource cannot be found.
 * <p>
 * Typically results in an HTTP 404 (Not Found) response.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Creates a new ressource not found exception.
     *
     * @param message error message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}