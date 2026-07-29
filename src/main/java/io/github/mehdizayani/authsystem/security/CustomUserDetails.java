package io.github.mehdizayani.authsystem.security;

import io.github.mehdizayani.authsystem.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Custom implementation of Spring Security's {@link UserDetails}.
 * <p>
 * Adapts the application's {@link User} entity to the contract
 * required by Spring Security during authentication and authorization.
 *
 * @author Mehdi Zayani
 * @since 1.0.0
 */
public class CustomUserDetails implements UserDetails {

    private final User user;

    /**
     * Creates a new {@code CustomUserDetails} instance.
     *
     * @param user the authenticated user
     */
    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * Returns the authorities granted to the authenticated user.
     *
     * @return the user's granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getCode()))
                .toList();
    }

    /**
     * Returns the user's encoded password.
     *
     * @return the encoded password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the authentication identifier.
     * <p>
     * The user's email address is used as the username for authentication.
     *
     * @return the user's email address
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Indicates whether the user's account has not expired.
     *
     * @return {@code true} if the account is valid; {@code false} otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return !user.isAccountExpired();
    }

    /**
     * Indicates whether the user's account is not locked.
     *
     * @return {@code true} if the account is not locked; {@code false} otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return !user.isAccountLocked();
    }

    /**
     * Indicates whether the user's credentials have not expired.
     *
     * @return {@code true} if the credentials are valid; {@code false} otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return !user.isCredentialsExpired();
    }

    /**
     * Indicates whether the user account is enabled.
     *
     * @return {@code true} if the account is enabled; {@code false} otherwise
     */
    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    /**
     * Returns the underlying domain user.
     *
     * @return the authenticated user entity
     */
    public User getUser() {
        return user;
    }
}