package io.github.mehdizayani.authsystem.role.entity;

import io.github.mehdizayani.authsystem.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a security role that can be assigned to one or more users.
 * <p>
 * Roles are used by Spring Security to perform role-based authorization
 * across protected application resources.
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity {

    /**
     * Unique identifier of the role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique role code (e.g. ROLE_ADMIN, ROLE_USER).
     */
    @Column(nullable = false, unique = true, length = 50)
    private String code;

    /**
     * Human-readable description of the role.
     */
    @Column(length = 255)
    private String description;
}