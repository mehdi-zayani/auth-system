package io.github.mehdizayani.authsystem.auth.controller;

import io.github.mehdizayani.authsystem.auth.dto.request.LoginRequest;
import io.github.mehdizayani.authsystem.auth.dto.request.RegisterRequest;
import io.github.mehdizayani.authsystem.auth.dto.response.AuthResponse;
import io.github.mehdizayani.authsystem.auth.dto.response.LoginResponse;
import io.github.mehdizayani.authsystem.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Exposes REST endpoints for user registration and authentication.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Registers a new user account.
     *
     * @param request registration request
     * @return registered user information
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    /**
     * Authenticates a user and returns a JWT access token.
     *
     * @param request login request
     * @return authentication response containing the JWT
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}