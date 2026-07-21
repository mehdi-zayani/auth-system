package io.github.mehdizayani.authsystem.auth.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(

        String accessToken,
        String tokenType,
        long expiresIn

) {
}