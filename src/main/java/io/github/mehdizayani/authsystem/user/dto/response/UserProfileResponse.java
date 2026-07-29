package io.github.mehdizayani.authsystem.user.dto.response;

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
public record UserProfileResponse(
        Long id,
        String firstName,
        String lastName,
        String username,
        String email,
        boolean enabled,
        boolean emailVerified,
        List<String> roles
) {
}