package io.github.mehdizayani.authsystem.exception;

/**
 * Exception thrown when an unexpected server-side error occurs.
 * <p>
 * Typically results in an HTTP 500 (Internal Server Error) response.
 */
public class InternalServerException extends RuntimeException {

    /**
     * Creates a new internal server exception.
     *
     * @param message error message returned to the client
     */
    public InternalServerException(String message) {
        super(message);
    }
    /**
     * Creates a new internal server exception.
     *
     * @param message error message
     * @param cause the underlying cause of the exception
     */
    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }
}