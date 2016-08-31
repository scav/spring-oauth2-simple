package com.devbugger.spring.oauth2.test;

import com.devbugger.spring.oauth2.TestContext;
import com.devbugger.spring.oauth2.user.LocalUser;
import com.devbugger.spring.oauth2.user.LocalUserDetails;
import com.devbugger.spring.oauth2.user.LocalUserDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("unchecked")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContext.class)
public class WithOauthDetailsSecurityContextFactoryTest {

    private static final String USER_NAME = "pmanpat";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_USER = "ROLE_USER";

    @Autowired
    private LocalUserDetailsService userDetailsService;


    @Test
    @WithOauthDetails(username = USER_NAME, authorities = {ROLE_ADMIN, ROLE_USER})
    public void testOauthDetails() throws Exception {
        LocalUserDetails localUserDetails = (LocalUserDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        LocalUser localUser = ((LocalUserDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();

        assertThat("Username should be " + USER_NAME, localUser.getUsername(), is(USER_NAME));

        Collection<SimpleGrantedAuthority> auth = (Collection<SimpleGrantedAuthority>) localUserDetails.getAuthorities();
        assertThat("Authorities should contain all given authorities",
                auth,
                containsInAnyOrder(
                        new SimpleGrantedAuthority(ROLE_ADMIN),
                        new SimpleGrantedAuthority(ROLE_USER)));

    }

}
