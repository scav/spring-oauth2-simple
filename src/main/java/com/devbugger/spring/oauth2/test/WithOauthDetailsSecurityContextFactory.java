package com.devbugger.spring.oauth2.test;

import com.devbugger.spring.oauth2.user.LocalUser;
import com.devbugger.spring.oauth2.user.LocalUserDetailsService;
import com.devbugger.spring.oauth2.user.LocalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Dag Østgulen Heradstveit.
 */
final class WithOauthDetailsSecurityContextFactory implements
        WithSecurityContextFactory<WithOauthDetails> {

    private LocalUserDetailsService localUserDetailsService;
    private LocalUserService localUserService;

    @Autowired
    public WithOauthDetailsSecurityContextFactory(LocalUserDetailsService localUserDetailsService, LocalUserService localUserService) {
        this.localUserDetailsService = localUserDetailsService;
        this.localUserService = localUserService;
    }

    @Override
    public SecurityContext createSecurityContext(WithOauthDetails oauthUser) {
        Assert.hasLength(oauthUser.username(), "username() must be non empty String");
        Assert.notEmpty(oauthUser.authorities(), "authorities() must be non empty String Array");

        String username = oauthUser.username();
        String[] authorities = oauthUser.authorities();

        UserDetails principal = localUserDetailsService.loadUserByExtractedAuthentication(
                generateRequestMap(username, authorities));
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal, principal.getPassword(), principal.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;
    }

    /**
     * Minimal representation of an incoming user. The user is fetched from the local database
     * if it exists.
     * @param username the... username
     * @param authorities authorities for the user
     * @return a map pretending to be network originated
     */
    public final Map<String, Object> generateRequestMap(String username, String...authorities) {
        LocalUser user = localUserService.findByUsername(username);
        Assert.notNull(user, "UserService returned null. Does user " + username + " exist?");

        final Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("user_email", user.getUsername());
        map.put("user_id", user.getId().toString());
        map.put("scope", Collections.singletonList("write"));
        map.put("authorities", Arrays.asList(authorities));
        map.put("exp", 1470969899L);
        map.put("jti", "5c0fe75d-30fa-4e9a-a9bf-a23d7dcdf223");
        map.put("client_id", "test");

        return map;
    }
}
