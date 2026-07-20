package io.github.mehdizayani.authsystem.exception.response;

import java.time.Instant;
import java.util.List;
import lombok.Builder;

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