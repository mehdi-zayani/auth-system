package io.github.mehdizayani.authsystem.role.repository;

import io.github.mehdizayani.authsystem.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Repository for managing {@link Role} entities.
 * <p>
 * Provides database access operations for application roles.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Retrieves a role by its unique code.
     *
     * @param code the role code (e.g. ROLE_USER, ROLE_ADMIN)
     * @return an {@link Optional} containing the role if found, otherwise empty
     */
    Optional<Role> findByCode(String code);
}