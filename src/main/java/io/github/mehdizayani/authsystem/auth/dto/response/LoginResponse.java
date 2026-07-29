package io.github.mehdizayani.authsystem.auth.dto.response;

import lombok.Builder;

/**
 * Response returned after a successful authentication.
 *
 * @param accessToken JWT access token
 * @param tokenType authentication token type
 * @param expiresIn token expiration time in seconds
 */
@Builder
public record LoginResponse(

        String accessToken,
        String tokenType,
        long expiresIn

) {
}