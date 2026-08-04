package io.github.mehdizayani.authsystem.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

/**
 * Response returned for the authenticated user's profile.
 * <p>
 * Contains public account information and the list of
 * granted roles associated with the authenticated user.
 *
 * @author Mehdi Zayani
 * @since 1.0.0
 */
@Builder
@Schema(description = "Authenticated user profile")
public record UserProfileResponse(

        @Schema(description = "User identifier", example = "1")
        Long id,

        @Schema(description = "User first name", example = "Mehdi")
        String firstName,

        @Schema(description = "User last name", example = "Zayani")
        String lastName,

        @Schema(description = "Unique username", example = "mehdi")
        String username,

        @Schema(description = "User email address", example = "mehdi@test.com")
        String email,

        @Schema(description = "Indicates whether the account is enabled", example = "true")
        boolean enabled,

        @Schema(description = "Indicates whether the email address has been verified", example = "true")
        boolean emailVerified,

        @Schema(description = "Granted roles")
        List<String> roles

) {
}