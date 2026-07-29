package io.github.mehdizayani.authsystem.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Request payload used to authenticate an existing user.
 *
 * @param email user email address
 * @param password user password
 */
public record LoginRequest(

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Password is required")
        String password

) {
}