package com.devbugger.spring.oauth2.user;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

/**
 * Created by Dag Østgulen Heradstveit.
 */
public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {

    UserDetails loadUserByExtractedAuthentication(Map<String, ?> requestMap);
}