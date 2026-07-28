package io.github.mehdizayani.authsystem.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;


import java.util.function.Function;
import javax.crypto.SecretKey;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim(
                        "roles",
                        userDetails.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .toList()
                )
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + jwtProperties.getExpiration()
                        )
                )
                .signWith(getSigningKey())
                .compact();
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver
    ) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(
            String token,
            UserDetails userDetails
    ) {

        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration)
                .before(new Date());
    }

    private Claims extractAllClaims(String token) {

        Jws<Claims> claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);

        return claims.getPayload();
    }
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtProperties.getSecretKey())
        );

    }
}