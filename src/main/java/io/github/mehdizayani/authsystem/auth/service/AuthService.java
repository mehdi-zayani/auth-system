package io.github.mehdizayani.authsystem.auth.service;

import io.github.mehdizayani.authsystem.auth.dto.request.LoginRequest;
import io.github.mehdizayani.authsystem.auth.dto.request.RegisterRequest;
import io.github.mehdizayani.authsystem.auth.dto.response.AuthResponse;
import io.github.mehdizayani.authsystem.auth.dto.response.LoginResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);

}