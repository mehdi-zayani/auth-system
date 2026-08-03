package io.github.mehdizayani.authsystem.security.jwt;

import io.github.mehdizayani.authsystem.security.CustomUserDetails;
import io.github.mehdizayani.authsystem.user.entity.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;


class JwtServiceTest {


    private JwtService jwtService;

    private CustomUserDetails userDetails;


    @BeforeEach
    void setUp() {

        JwtProperties properties = new JwtProperties();

        properties.setSecretKey(
                "VGhpc0lzQVNlY3VyZVNlY3JldEtleUZvckpXVFRlc3RpbmdBbmRNdXN0QmVsb25nVG9UaGUyNTZiaXJLZXk="
        );

        properties.setExpiration(3600000);


        jwtService = new JwtService(properties);


        User user = User.builder()
                .email("test@test.com")
                .password("password")
                .build();


        userDetails = new CustomUserDetails(user);
    }



    @Test
    void shouldGenerateTokenSuccessfully() {


        String token =
                jwtService.generateToken(userDetails);


        assertThat(token)
                .isNotBlank();

        assertThat(
                jwtService.extractUsername(token)
        )
                .isEqualTo("test@test.com");
    }



    @Test
    void shouldValidateTokenSuccessfully() {


        String token =
                jwtService.generateToken(userDetails);


        boolean valid =
                jwtService.isTokenValid(
                        token,
                        userDetails
                );


        assertThat(valid)
                .isTrue();
    }



    @Test
    void shouldReturnFalseWhenUsernameDoesNotMatch() {


        String token =
                jwtService.generateToken(userDetails);


        User anotherUser = User.builder()
                .email("other@test.com")
                .password("password")
                .build();


        CustomUserDetails anotherUserDetails =
                new CustomUserDetails(anotherUser);



        boolean valid =
                jwtService.isTokenValid(
                        token,
                        anotherUserDetails
                );


        assertThat(valid)
                .isFalse();
    }
}