package io.github.mehdizayani.authsystem.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Base JPA entity providing automatic auditing fields.
 * <p>
 * Stores creation and last update timestamps for all entities
 * extending this class.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Initializes auditing fields before the entity is persisted.
     */
    @PrePersist
    protected void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    /**
     * Updates the last modification timestamp before the entity is updated.
     */
    @PreUpdate
    protected void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}