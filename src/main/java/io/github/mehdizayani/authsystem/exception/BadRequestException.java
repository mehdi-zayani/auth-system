package io.github.mehdizayani.authsystem.exception;

/**
 * Exception thrown when a client submits an invalid request.
 * <p>
 * Results in an HTTP 400 (Bad Request) response.
 */
public class BadRequestException extends RuntimeException {

    /**
     * Creates a new bad request exception.
     *
     * @param message the exception message
     */
    public BadRequestException(String message) {
        super(message);
    }
}