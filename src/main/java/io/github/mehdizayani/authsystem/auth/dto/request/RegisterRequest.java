package io.github.mehdizayani.authsystem.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request payload used to register a new user account.
 *
 * @param firstName user's first name
 * @param lastName user's last name
 * @param username unique username
 * @param email user email address
 * @param password user password
 */
@Schema(
        name = "RegisterRequest",
        description = "Request used to register a new user"
)
public record RegisterRequest(

        @Schema(description = "User first name", example = "Mehdi")
        @NotBlank(message = "First name is required")
        @Size(max = 100, message = "First name must not exceed 100 characters")
        String firstName,

        @Schema(description = "User last name", example = "Zayani")
        @NotBlank(message = "Last name is required")
        @Size(max = 100, message = "Last name must not exceed 100 characters")
        String lastName,

        @Schema(description = "Unique username", example = "mehdi")
        @NotBlank(message = "Username is required")
        @Size(max = 50, message = "Username must not exceed 50 characters")
        String username,


        @Schema(description = "User email address", example = "mehdi@test.com")
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        @Size(max = 255, message = "Email must not exceed 255 characters")
        String email,

        @Schema(description = "User password", example = "password123")
        @NotBlank(message = "Password is required")
        @Size(
                min = 8,
                max = 255,
                message = "Password must be between 8 and 255 characters"
        )
        String password

) {
}