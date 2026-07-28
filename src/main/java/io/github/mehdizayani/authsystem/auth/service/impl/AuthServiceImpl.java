package io.github.mehdizayani.authsystem.auth.service.impl;

import io.github.mehdizayani.authsystem.auth.dto.request.LoginRequest;
import io.github.mehdizayani.authsystem.auth.dto.request.RegisterRequest;
import io.github.mehdizayani.authsystem.auth.dto.response.AuthResponse;
import io.github.mehdizayani.authsystem.auth.dto.response.LoginResponse;
import io.github.mehdizayani.authsystem.auth.service.AuthService;
import io.github.mehdizayani.authsystem.exception.ConflictException;
import io.github.mehdizayani.authsystem.exception.ResourceNotFoundException;
import io.github.mehdizayani.authsystem.role.entity.Role;
import io.github.mehdizayani.authsystem.role.repository.RoleRepository;
import io.github.mehdizayani.authsystem.security.CustomUserDetails;
import io.github.mehdizayani.authsystem.security.CustomUserDetailsService;
import io.github.mehdizayani.authsystem.security.jwt.JwtService;
import io.github.mehdizayani.authsystem.user.entity.User;
import io.github.mehdizayani.authsystem.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new ConflictException("Email already exists");
        }

        if (userRepository.existsByUsername(request.username())) {
            throw new ConflictException("Username already exists");
        }


        Role userRole = roleRepository.findByCode("ROLE_USER")
                .orElseThrow(() ->
                        new ResourceNotFoundException("Default role USER not found")
                );


        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .roles(Set.of(userRole))
                .build();


        User savedUser = userRepository.save(user);


        return AuthResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .roles(
                        savedUser.getRoles()
                                .stream()
                                .map(Role::getCode)
                                .collect(Collectors.toSet())
                )
                .build();
    }
    @Override
    public LoginResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        return LoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(3600)
                .build();
    }
}

