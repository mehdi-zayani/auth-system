package io.github.mehdizayani.authsystem.exception.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

/**
 * Represents a validation error for a single request field.
 * <p>
 * Contains the field name and the corresponding validation message.
 *
 * @author Mehdi Zayani
 * @since 1.0.0
 */
@Builder
@Schema(
        name = "ValidationError",
        description = "Validation error for a single request field"
)
public record ValidationError(

        @Schema(
                description = "Field that failed validation",
                example = "email"
        )
        String field,

        @Schema(
                description = "Validation error message",
                example = "Email must be valid"
        )
        String message

) {
}