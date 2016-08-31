package com.devbugger.spring.oauth2.user;

import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;
import java.util.UUID;

public interface LocalUserService<U extends LocalUser> {

    U findById(UUID id);

    U findByUsername(String username);

    /**
     * Initialize a user on first contact based on incomming data from
     * the authorization server.
     *
     * @param oAuth2Authentication from authorization server
     */
    void initializeUser(OAuth2Authentication oAuth2Authentication);

    /**
     * Initialize a user on first contact based on data in the JWT.
     *
     * @param requestMap map containing data from JWT
     */
    U initializeFromJWT(Map<String, ?> requestMap);
}
