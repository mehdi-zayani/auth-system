package io.github.mehdizayani.authsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the Auth System application.
 * <p>
 * Bootstraps the Spring Boot application and initializes
 * all configured components.
 *
 * @author Mehdi Zayani
 * @since 1.0.0
 */
@SpringBootApplication
public class AuthSystemApplication {

	/**
	 * Starts the Spring Boot application.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AuthSystemApplication.class, args);
	}

}