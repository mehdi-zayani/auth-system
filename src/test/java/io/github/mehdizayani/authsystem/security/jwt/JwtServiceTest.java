package io.github.mehdizayani.authsystem.security.jwt;

import io.github.mehdizayani.authsystem.security.CustomUserDetails;
import io.github.mehdizayani.authsystem.user.entity.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link JwtService}.
 * <p>
 * Verifies JWT generation, username extraction
 * and token validation.
 */
class JwtServiceTest {


    private JwtService jwtService;

    private CustomUserDetails userDetails;

    /**
     * Initializes the JWT service and test user
     * before each test.
     */
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


    /**
     * Verifies that a JWT is successfully generated
     * and contains the expected username.
     */
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


    /**
     * Verifies that a generated JWT is considered valid
     * for the corresponding user.
     */
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


    /**
     * Verifies that token validation fails when the
     * token does not belong to the provided user.
     */
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