package io.github.mehdizayani.authsystem.role.repository;

import io.github.mehdizayani.authsystem.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByCode(String code);
}
