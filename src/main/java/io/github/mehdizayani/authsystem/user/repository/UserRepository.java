package io.github.mehdizayani.authsystem.user.repository;

import io.github.mehdizayani.authsystem.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Repository for managing {@link User} entities.
 * <p>
 * Provides CRUD operations and custom queries used by the
 * authentication and authorization components.
 *
 * @author Mehdi Zayani
 * @since 1.0.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    /**
     * Retrieves a user together with all assigned roles.
     * <p>
     * This query uses a fetch join to eagerly load the user's roles,
     * avoiding additional queries during authentication.
     *
     * @param email the user's email address
     * @return the matching user with roles, if found
     */
    @Query("""
            SELECT u
            FROM User u
            LEFT JOIN FETCH u.roles
            WHERE u.email = :email
            """)
    Optional<User> findByEmailWithRoles(String email);
}