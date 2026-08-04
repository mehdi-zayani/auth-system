package io.github.mehdizayani.authsystem.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

/**
 * Response returned after a successful authentication.
 *
 * @param accessToken JWT access token
 * @param tokenType authentication token type
 * @param expiresIn token expiration time in seconds
 */
@Builder
@Schema(
        name = "LoginResponse",
        description = "Authentication response"
)
public record LoginResponse(

        @Schema(
                description = "JWT access token"
        )
        String accessToken,

        @Schema(
                description = "Token type",
                example = "Bearer"
        )
        String tokenType,

        @Schema(
                description = "Token expiration in seconds",
                example = "3600"
        )
        long expiresIn

) {
}