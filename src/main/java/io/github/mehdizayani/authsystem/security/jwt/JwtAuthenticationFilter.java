package io.github.mehdizayani.authsystem.security.jwt;

import io.github.mehdizayani.authsystem.security.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT authentication filter executed once per request.
 * <p>
 * Extracts the JWT from the Authorization header, validates it,
 * loads the corresponding user and populates the Spring Security
 * {@link SecurityContextHolder} when authentication succeeds.
 *
 * @author Mehdi Zayani
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Authenticates incoming requests using a Bearer JWT.
     * <p>
     * If the token is valid, an authenticated
     * {@link UsernamePasswordAuthenticationToken} is stored in the
     * current security context before continuing the filter chain.
     *
     * @param request incoming HTTP request
     * @param response outgoing HTTP response
     * @param filterChain Spring Security filter chain
     * @throws ServletException if request processing fails
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        String email = jwtService.extractUsername(token);

        if (email != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    customUserDetailsService.loadUserByUsername(email);

            if (jwtService.isTokenValid(token, userDetails)) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}