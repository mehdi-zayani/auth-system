package io.github.mehdizayani.authsystem.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures the OpenAPI documentation exposed by Swagger UI.
 * <p>
 * Defines the API metadata and the JWT Bearer authentication
 * scheme used to authorize protected endpoints.
 *
 * @author Mehdi Zayani
 * @since 1.0.0
 */
@Configuration
public class OpenApiConfiguration {

    /**
     * Creates the OpenAPI configuration for the Auth System API.
     *
     * @return configured OpenAPI instance
     */
    @Bean
    public OpenAPI authSystemOpenAPI() {

        final String securitySchemeName = "Bearer Authentication";

        return new OpenAPI()

                .info(
                        new Info()
                                .title("Auth System API")
                                .description("""
                                        Production-ready authentication and authorization API
                                        built with Spring Boot, Spring Security and JWT.
                                        """)
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .name("Mehdi Zayani")
                                                .url("https://github.com/mehdi-zayani")
                                                .email("ymehdi.zayani.dev@gmail.com")
                                )
                                .license(
                                        new License()
                                                .name("MIT")
                                                .url("https://opensource.org/licenses/MIT")
                                )
                )

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(securitySchemeName)
                )

                .components(
                        new Components()
                                .addSecuritySchemes(
                                        securitySchemeName,
                                        new SecurityScheme()
                                                .name("Authorization")
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }

}