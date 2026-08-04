package io.github.mehdizayani.authsystem.security;

import io.github.mehdizayani.authsystem.configuration.SecurityConfiguration;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import org.springframework.context.annotation.Import;

import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for {@link SecurityConfiguration}.
 * <p>
 * Verifies that security rules are correctly applied:
 * public authentication endpoints are accessible without JWT,
 * protected endpoints require authentication,
 * authenticated users can access secured resources.
 *
 * @author Mehdi Zayani
 * @since 1.0.0
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import({
        SecurityConfiguration.class,
        SecurityConfigurationTest.TestController.class
})
class SecurityConfigurationTest {
    /**
     * MockMvc instance used to perform HTTP requests
     * against the Spring Security filter chain.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Verifies that authentication endpoints are publicly accessible.
     * <p>
     * The endpoint is reachable without JWT authentication.
     * Validation errors are expected because the request body is empty.
     *
     * @throws Exception if the HTTP request fails
     */
    @Test
    void shouldAllowPublicAuthEndpointWithoutToken()
            throws Exception {

        mockMvc.perform(
                        post("/api/v1/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {}
                                        """)
                )
                .andExpect(status().isBadRequest());
    }

    /**
     * Verifies that protected endpoints reject unauthenticated requests.
     *
     * @throws Exception if the HTTP request fails
     */
    @Test
    void shouldBlockProtectedEndpointWithoutAuthentication()
            throws Exception {

        mockMvc.perform(
                        get("/api/v1/test/protected")
                )
                .andExpect(status().isUnauthorized());
    }

    /**
     * Verifies that authenticated users can access protected resources.
     *
     * @throws Exception if the HTTP request fails
     */
    @Test
    @WithMockUser(username = "test@test.com")
    void shouldAllowAuthenticatedUser()
            throws Exception {

        mockMvc.perform(
                        get("/api/v1/test/protected")
                )
                .andExpect(status().isOk());
    }
    /**
     * Minimal controller used only for testing secured endpoints.
     * <p>
     * This avoids depending on application controllers and allows
     * isolated verification of Spring Security behavior.
     */
    @RestController
    static class TestController {
        /**
         * Protected endpoint used to validate authentication rules.
         *
         * @return secured response message
         */
        @GetMapping("/api/v1/test/protected")
        public String protectedEndpoint() {

            return "secured";
        }
    }
}