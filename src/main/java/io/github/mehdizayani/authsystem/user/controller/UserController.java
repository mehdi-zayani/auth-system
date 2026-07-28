package io.github.mehdizayani.authsystem.user.controller;

import io.github.mehdizayani.authsystem.security.CustomUserDetails;
import io.github.mehdizayani.authsystem.user.dto.response.UserProfileResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

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
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Admin area";
    }
}