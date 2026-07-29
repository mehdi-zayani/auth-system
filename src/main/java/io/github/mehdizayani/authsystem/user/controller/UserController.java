package io.github.mehdizayani.authsystem.user.controller;

import io.github.mehdizayani.authsystem.security.CustomUserDetails;
import io.github.mehdizayani.authsystem.user.dto.response.UserProfileResponse;
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
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    /**
     * Returns the profile of the currently authenticated user.
     *
     * @param userDetails the authenticated user's security principal
     * @return the authenticated user's profile information
     */
    @GetMapping("/me")
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
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Admin area";
    }

}