package io.github.mehdizayani.authsystem.user.controller;

import io.github.mehdizayani.authsystem.exception.response.ApiErrorResponse;
import io.github.mehdizayani.authsystem.security.CustomUserDetails;
import io.github.mehdizayani.authsystem.user.dto.response.UserProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing user-related endpoints.
 * <p>
 * Provides authenticated users with access to their profile
 * information and demonstrates role-based authorization.
 *
 * @author Mehdi Zayani
 * @since 1.0.0
 */
@Tag(
        name = "Users",
        description = "User management endpoints"
)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    /**
     * Returns the profile of the currently authenticated user.
     *
     * @param userDetails the authenticated user's security principal
     * @return the authenticated user's profile information
     */
    @Operation(
            summary = "Get authenticated user profile",
            description = "Returns the profile information of the currently "
                    + "authenticated user."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Profile retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserProfileResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication required",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    })
    @GetMapping(
            value = "/me",
            produces = "application/json"
    )
    public UserProfileResponse me(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        return UserProfileResponse.builder()
                .id(userDetails.getUser().getId())
                .firstName(userDetails.getUser().getFirstName())
                .lastName(userDetails.getUser().getLastName())
                .username(userDetails.getUser().getUsername())
                .email(userDetails.getUser().getEmail())
                .enabled(userDetails.getUser().isEnabled())
                .emailVerified(userDetails.getUser().isEmailVerified())
                .roles(
                        userDetails.getAuthorities()
                                .stream()
                                .map(authority -> authority.getAuthority())
                                .toList()
                )
                .build();
    }

    /**
     * Returns a resource accessible only to users with the ADMIN role.
     *
     * @return a confirmation message for administrators
     */
    @Operation(
            summary = "Admin only endpoint",
            description = "Returns a resource accessible only to users "
                    + "granted the ADMIN role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Access granted",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(
                                    type = "string",
                                    example = "Admin area"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication required",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    })
    @GetMapping(
            value = "/admin",
            produces = "application/json"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {

        return "Admin area";
    }

}