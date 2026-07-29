package io.github.mehdizayani.authsystem.exception.response;

import java.time.Instant;
import lombok.Builder;

/**
 * Standard API error response returned by the application.
 * <p>
 * Contains the metadata and details required by clients to identify
 * and troubleshoot request processing errors.
 */
@Builder
public record ApiErrorResponse(
        Instant timestamp,
        int status,
        String code,
        String error,
        String message,
        String path,
        String traceId
) {
}