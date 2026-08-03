package io.github.mehdizayani.authsystem.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mehdizayani.authsystem.auth.dto.request.LoginRequest;
import io.github.mehdizayani.authsystem.auth.dto.request.RegisterRequest;
import io.github.mehdizayani.authsystem.auth.dto.response.AuthResponse;
import io.github.mehdizayani.authsystem.auth.dto.response.LoginResponse;
import io.github.mehdizayani.authsystem.auth.service.AuthService;
import io.github.mehdizayani.authsystem.security.CustomUserDetailsService;
import io.github.mehdizayani.authsystem.security.jwt.JwtAuthenticationFilter;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Set;


@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;


    @Test
    void shouldRegisterUserSuccessfully() throws Exception {

        RegisterRequest request =
                new RegisterRequest(
                        "Mehdi",
                        "Zayani",
                        "mehdi",
                        "mehdi@test.com",
                        "password123"
                );


        AuthResponse response =
                AuthResponse.builder()
                        .id(1L)
                        .email("mehdi@test.com")
                        .roles(Set.of("ROLE_USER"))
                        .build();


        when(authService.register(any(RegisterRequest.class)))
                .thenReturn(response);


        mockMvc.perform(
                        post("/api/v1/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("mehdi@test.com"))
                .andExpect(jsonPath("$.roles[0]").value("ROLE_USER"));
    }


    @Test
    void shouldLoginSuccessfully() throws Exception {

        LoginRequest request =
                new LoginRequest(
                        "mehdi@test.com",
                        "password123"
                );


        LoginResponse response =
                LoginResponse.builder()
                        .accessToken("jwt-token")
                        .tokenType("Bearer")
                        .expiresIn(3600)
                        .build();


        when(authService.login(any(LoginRequest.class)))
                .thenReturn(response);


        mockMvc.perform(
                        post("/api/v1/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("jwt-token"))
                .andExpect(jsonPath("$.tokenType").value("Bearer"));
    }


    @Test
    void shouldRejectInvalidRegisterRequest() throws Exception {

        RegisterRequest request =
                new RegisterRequest(
                        "",
                        "",
                        "",
                        "invalid-email",
                        ""
                );


        mockMvc.perform(
                        post("/api/v1/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest());
    }
}