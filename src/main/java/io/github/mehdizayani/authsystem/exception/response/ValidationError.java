package io.github.mehdizayani.authsystem.exception.response;

import lombok.Builder;

@Builder
public record ValidationError(
        String field,
        String message
) {
}