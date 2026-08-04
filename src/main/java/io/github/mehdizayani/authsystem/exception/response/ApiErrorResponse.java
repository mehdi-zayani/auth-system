package io.github.mehdizayani.authsystem.exception.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;

/**
 * Standard API error response returned by the application.
 * <p>
 * Contains the metadata and details required by clients to identify
 * and troubleshoot request processing errors.
 *
 * @author Mehdi Zayani
 * @since 1.0.0
 */
@Builder
@Schema(
        name = "ApiErrorResponse",
        description = "Standard API error response"
)
public record ApiErrorResponse(

        @Schema(
                description = "Timestamp when the error occurred",
                example = "2026-08-03T10:15:30Z"
        )
        Instant timestamp,

        @Schema(
                description = "HTTP status code",
                example = "404"
        )
        int status,

        @Schema(
                description = "Application-specific error code",
                example = "RESOURCE_NOT_FOUND"
        )
        String code,

        @Schema(
                description = "HTTP error name",
                example = "Not Found"
        )
        String error,

        @Schema(
                description = "Detailed error message",
                example = "User with id 1 was not found."
        )
        String message,

        @Schema(
                description = "Request path",
                example = "/api/v1/users/1"
        )
        String path,

        @Schema(
                description = "Unique request trace identifier",
                example = "7b9c4d8e2f4a1c3d"
        )
        String traceId

) {
}