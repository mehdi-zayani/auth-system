package io.github.mehdizayani.authsystem.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Set;

/**
 * Response returned after a successful user registration.
 *
 * @param id unique user identifier
 * @param email user email address
 * @param roles assigned user roles
 */
@Builder
@Schema(
        name = "AuthResponse",
        description = "User registration response"
)
public record AuthResponse(

        @Schema(
                description = "User identifier",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Registered email",
                example = "mehdi@test.com"
        )
        String email,

        @Schema(
                description = "Granted roles"
        )
        Set<String> roles

) {
}