package io.github.mehdizayani.authsystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Basic application context loading test.
 * <p>
 * Ensures that the Spring Boot application context starts successfully
 * and that all required beans can be initialized.
 *
 * @author Mehdi Zayani
 * @since 1.0.0
 */
@SpringBootTest
class AuthSystemApplicationTests {

	/**
	 * Verifies that the application context loads without errors.
	 *
	 * <p>
	 * This test acts as a smoke test to detect configuration issues,
	 * missing beans, or startup failures.
	 */
	@Test
	void contextLoads() {
	}

}