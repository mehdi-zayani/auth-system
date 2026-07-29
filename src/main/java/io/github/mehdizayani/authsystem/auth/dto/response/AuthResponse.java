package io.github.mehdizayani.authsystem.auth.dto.response;

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
public record AuthResponse(

        Long id,
        String email,
        Set<String> roles

) {
}