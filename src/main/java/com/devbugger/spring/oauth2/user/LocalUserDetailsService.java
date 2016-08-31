package com.devbugger.spring.oauth2.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

import static org.springframework.security.oauth2.provider.token.UserAuthenticationConverter.AUTHORITIES;

public final class LocalUserDetailsService implements UserDetailsService {

    private LocalUserService userService;

    public LocalUserDetailsService(LocalUserService userService) {
        this.userService = userService;
    }

    @Override
    public LocalUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return load(userService.findByUsername(username), null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public LocalUserDetails loadUserByExtractedAuthentication(Map<String, ?> requestMap) {
        UUID id = UUID.fromString((String)requestMap.get("user_id"));
        LocalUser localUser = userService.findById(id);
        if(localUser != null) {
            return load(localUser, requestMap.get(AUTHORITIES));
        }

        return load(userService.initializeFromJWT(requestMap), requestMap.get(AUTHORITIES));
    }

    private LocalUserDetails load(LocalUser localUser, Object authorities) {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        if (authorities instanceof String) {
            grantedAuthorities.add(new SimpleGrantedAuthority((String) authorities));
        }
        if (authorities instanceof Collection) {
            for(Object authority: (Collection)authorities) {
                grantedAuthorities.add(new SimpleGrantedAuthority((String) authority));
            }
        }

        return new LocalUserDetails(localUser, grantedAuthorities);
    }
}