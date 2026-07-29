package io.github.mehdizayani.authsystem.exception.response;

import lombok.Builder;

/**
 * Represents a validation error for a single request field.
 * <p>
 * Contains the field name and the corresponding validation message.
 */
@Builder
public record ValidationError(
        String field,
        String message
) {
}