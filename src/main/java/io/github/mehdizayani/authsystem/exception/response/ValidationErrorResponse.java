package io.github.mehdizayani.authsystem.exception.response;

import java.time.Instant;
import java.util.List;
import lombok.Builder;

/**
 * Standard API response returned when request validation fails.
 * <p>
 * Contains the general error information together with the list of
 * field-specific validation errors.
 */
@Builder
public record ValidationErrorResponse(
        Instant timestamp,
        int status,
        String code,
        String error,
        String message,
        String path,
        String traceId,
        List<ValidationError> errors
) {
}