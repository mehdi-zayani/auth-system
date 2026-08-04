package io.github.mehdizayani.authsystem.exception.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

/**
 * Standard API response returned when request validation fails.
 * <p>
 * Contains the general error information together with the list of
 * field-specific validation errors.
 *
 * @author Mehdi Zayani
 * @since 1.0.0
 */
@Builder
@Schema(
        name = "ValidationErrorResponse",
        description = "Validation error response"
)
public record ValidationErrorResponse(

        @Schema(
                description = "Timestamp when the validation error occurred",
                example = "2026-08-03T10:15:30Z"
        )
        Instant timestamp,

        @Schema(
                description = "HTTP status code",
                example = "400"
        )
        int status,

        @Schema(
                description = "Application-specific error code",
                example = "VALIDATION_ERROR"
        )
        String code,

        @Schema(
                description = "HTTP error name",
                example = "Bad Request"
        )
        String error,

        @Schema(
                description = "General validation error message",
                example = "Validation failed"
        )
        String message,

        @Schema(
                description = "Request path",
                example = "/api/v1/auth/register"
        )
        String path,

        @Schema(
                description = "Unique request trace identifier",
                example = "7b9c4d8e2f4a1c3d"
        )
        String traceId,

        @Schema(
                description = "List of field validation errors"
        )
        List<ValidationError> errors

) {
}