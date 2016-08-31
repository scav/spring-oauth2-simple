package com.devbugger.spring.oauth2.oauth2;

import com.devbugger.spring.oauth2.user.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * Pre made converter setup for {@link org.springframework.context.annotation.Bean} used
 * in any given {@link org.springframework.context.annotation.Configuration} class.
 *
 * @author Dag Østgulen Heradstveit
 */
public final class LocalTokenConverter {

    private UserDetailsService userDetailsService;

    /**
     * The pub key used to read data from the token.
     */
    private String pubKey;

    /**
     * Set the actual implementation of the service implementing {@link UserDetailsService}
     * from this package, and not the one provided by the Spring Framework.
     * @param userDetailsService
     */
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Set the key that will be used to decrypt the encrypted string
     * from the oauth2 request.
     * @param pubKey
     */
    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    /**
     * Sets up the complete token enhancer / converter chain.
     * This should be initialized as a bean when implementing.
     * @return
     */
    public JwtAccessTokenConverter tokenEnhancer() {
        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(userAuthenticationConverter());

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(pubKey);
        converter.setAccessTokenConverter(defaultAccessTokenConverter);

        return converter;
    }

    private UserAuthenticationConverter userAuthenticationConverter() {
        LocalUserAuthenticationConverter converter = new LocalUserAuthenticationConverter();
        converter.setUserDetailsService(userDetailsService);
        return converter;
    }
}
