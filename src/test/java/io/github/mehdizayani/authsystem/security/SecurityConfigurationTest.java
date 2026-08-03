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

@SpringBootTest
@AutoConfigureMockMvc
@Import({
        SecurityConfiguration.class,
        SecurityConfigurationTest.TestController.class
})
class SecurityConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

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

    @Test
    void shouldBlockProtectedEndpointWithoutAuthentication()
            throws Exception {

        mockMvc.perform(
                        get("/api/v1/test/protected")
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "test@test.com")
    void shouldAllowAuthenticatedUser()
            throws Exception {

        mockMvc.perform(
                        get("/api/v1/test/protected")
                )
                .andExpect(status().isOk());
    }

    @RestController
    static class TestController {

        @GetMapping("/api/v1/test/protected")
        public String protectedEndpoint() {

            return "secured";
        }
    }
}