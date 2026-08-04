package io.github.mehdizayani.authsystem.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Request payload used to authenticate an existing user.
 *
 * @param email user email address
 * @param password user password
 */
@Schema(
        name = "LoginRequest",
        description = "Authentication request"
)
public record LoginRequest(


        @Schema(description = "User email", example = "mehdi@test.com")
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,

        @Schema(description = "User password", example = "password123")
        @NotBlank(message = "Password is required")
        String password

) {
}