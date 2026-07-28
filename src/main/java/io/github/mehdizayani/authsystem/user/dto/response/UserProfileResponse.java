package io.github.mehdizayani.authsystem.user.dto.response;

import lombok.Builder;

import java.util.List;

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