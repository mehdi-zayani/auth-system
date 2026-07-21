package io.github.mehdizayani.authsystem.auth.dto.response;

import lombok.Builder;

import java.util.Set;

@Builder
public record AuthResponse(

        Long id,
        String email,
        Set<String> roles

) {
}