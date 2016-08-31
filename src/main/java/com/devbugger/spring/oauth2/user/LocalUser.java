package com.devbugger.spring.oauth2.user;

import com.devbugger.spring.oauth2.oauth2.LocalUserAuthenticationConverter;

import java.util.UUID;

/**
 * Define a local user class by implementing this interface.
 *
 * Using this interface is a guarantee that your actual user
 * object will be picked up by
 * {@link LocalUserAuthenticationConverter}
 * {@link LocalUserDetails}
 * {@link LocalUserDetailsService}
 *
 */
public interface LocalUser {

    /**
     * Get the ID for this user as provided by the login server.
     * The login server uses UUID and that is enforced here.
     *
     * @return users id.
     */
    UUID getId();

    /**
     * Get the username for the user as provided by the login server.
     *
     * @return users username
     */
    String getUsername();
}