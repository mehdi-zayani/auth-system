package io.github.mehdizayani.authsystem.user.entity;

import io.github.mehdizayani.authsystem.common.BaseEntity;
import io.github.mehdizayani.authsystem.role.entity.Role;
import jakarta.persistence.*;

import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String firstName;
    @Column(nullable = false, length = 100)
    private String lastName;
    @Column(nullable = false, unique= true, length = 50)
    private String username;
    @Column(nullable = false, unique = true, length = 255)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    nullable = false
            )
    )
    private Set<Role> roles = new HashSet<>();
    @Builder.Default
    @Column(nullable = false)
    private boolean enabled = true;
    @Builder.Default
    @Column(nullable = false)
    private boolean accountLocked = false ;
    @Builder.Default
    @Column(nullable = false)
    private boolean credentialsExpired = false;
    @Builder.Default
    @Column(nullable = false)
    private boolean accountExpired = false;
    @Builder.Default
    @Column(nullable = false)
    private boolean emailVerified = false;





}
