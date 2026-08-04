package io.github.mehdizayani.authsystem.auth.service;

import io.github.mehdizayani.authsystem.auth.dto.request.LoginRequest;
import io.github.mehdizayani.authsystem.auth.dto.request.RegisterRequest;
import io.github.mehdizayani.authsystem.auth.dto.response.AuthResponse;
import io.github.mehdizayani.authsystem.auth.dto.response.LoginResponse;

/**
 * Defines authentication operations for user registration and login.
 */
public interface AuthService {

    /**
     * Registers a new user account.
     *
     * @param request registration request
     * @return registered user information
     */
    AuthResponse register(RegisterRequest request);

    /**
     * Authenticates a user and returns an access token.
     *
     * @param request login request
     * @return authentication response containing the JWT
     */
    LoginResponse login(LoginRequest request);

}