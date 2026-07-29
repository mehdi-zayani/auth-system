package io.github.mehdizayani.authsystem.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for JWT authentication.
 * <p>
 * Maps the JWT-related settings defined under the
 * {@code jwt} prefix in the application configuration.
 *
 * @author Mehdi Zayani
 * @since 1.0.0
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * Base64-encoded secret key used to sign and verify JWTs.
     */
    private String secretKey;

    /**
     * JWT expiration time in milliseconds.
     */
    private long expiration;
}