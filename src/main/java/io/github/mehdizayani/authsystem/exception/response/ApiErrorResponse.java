package io.github.mehdizayani.authsystem.exception.response;

import java.time.Instant;
import lombok.Builder;

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