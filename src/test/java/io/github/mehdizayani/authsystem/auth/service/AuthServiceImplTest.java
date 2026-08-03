package io.github.mehdizayani.authsystem.auth.service;

import io.github.mehdizayani.authsystem.auth.dto.request.RegisterRequest;
import io.github.mehdizayani.authsystem.auth.dto.response.AuthResponse;
import io.github.mehdizayani.authsystem.auth.service.impl.AuthServiceImpl;
import io.github.mehdizayani.authsystem.exception.ConflictException;
import io.github.mehdizayani.authsystem.role.entity.Role;
import io.github.mehdizayani.authsystem.role.repository.RoleRepository;
import io.github.mehdizayani.authsystem.user.entity.User;
import io.github.mehdizayani.authsystem.user.repository.UserRepository;
import io.github.mehdizayani.authsystem.security.jwt.JwtService;
import io.github.mehdizayani.authsystem.security.CustomUserDetailsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private CustomUserDetailsService customUserDetailsService;


    @InjectMocks
    private AuthServiceImpl authService;


    private Role userRole;


    @BeforeEach
    void setUp() {

        userRole = new Role();
        userRole.setId(1L);
        userRole.setCode("ROLE_USER");
    }


    @Test
    void shouldRegisterUserSuccessfully() {

        RegisterRequest request =
                new RegisterRequest(
                        "Mehdi",
                        "Zayani",
                        "mehdi",
                        "mehdi@test.com",
                        "password123"
                );


        when(userRepository.existsByEmail(request.email()))
                .thenReturn(false);

        when(userRepository.existsByUsername(request.username()))
                .thenReturn(false);

        when(roleRepository.findByCode("ROLE_USER"))
                .thenReturn(Optional.of(userRole));

        when(passwordEncoder.encode(request.password()))
                .thenReturn("encoded-password");


        User savedUser = User.builder()
                .id(1L)
                .email(request.email())
                .username(request.username())
                .password("encoded-password")
                .roles(java.util.Set.of(userRole))
                .build();


        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);



        AuthResponse response =
                authService.register(request);



        assertThat(response.id())
                .isEqualTo(1L);

        assertThat(response.email())
                .isEqualTo(request.email());

        assertThat(response.roles())
                .contains("ROLE_USER");


        verify(userRepository)
                .save(any(User.class));
    }



    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {


        RegisterRequest request =
                new RegisterRequest(
                        "Mehdi",
                        "Zayani",
                        "mehdi",
                        "mehdi@test.com",
                        "password123"
                );


        when(userRepository.existsByEmail(request.email()))
                .thenReturn(true);



        assertThatThrownBy(() ->
                authService.register(request)
        )
                .isInstanceOf(ConflictException.class);


        verify(userRepository, never())
                .save(any());
    }
}