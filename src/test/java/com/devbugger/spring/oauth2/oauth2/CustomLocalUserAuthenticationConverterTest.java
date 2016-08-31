package com.devbugger.spring.oauth2.oauth2;

import com.devbugger.spring.oauth2.mocks.DataMock;
import com.devbugger.spring.oauth2.mocks.UserServiceMock;
import com.devbugger.spring.oauth2.user.LocalUserDetails;
import com.devbugger.spring.oauth2.user.LocalUserDetailsService;
import com.devbugger.spring.oauth2.user.UserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Collection;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("unchecked")
@RunWith(JUnit4.class)
public class CustomLocalUserAuthenticationConverterTest {

    private Map<String, Object> map = new DataMock().generateMap().getMap();
    private LocalUserAuthenticationConverter authenticationConverter;

    @Before
    public void setup() {
        UserDetailsService userDetailsService = new LocalUserDetailsService(new UserServiceMock());
        authenticationConverter = new LocalUserAuthenticationConverter();
        authenticationConverter.setUserDetailsService(userDetailsService);
    }

    @Test
    public void extractAuthentication() throws Exception {
        Authentication authentication = authenticationConverter.extractAuthentication(map);

        assertTrue("Authentication principal should be of LocalUserDetails",
                authentication.getPrincipal() instanceof LocalUserDetails);

        Collection<SimpleGrantedAuthority> auth = (Collection<SimpleGrantedAuthority>) authentication.getAuthorities();


        assertThat("Authentication should contain the correct roles",
                auth,
                containsInAnyOrder(
                        new SimpleGrantedAuthority("ROLE_TESTER"),
                        new SimpleGrantedAuthority("ROLE_USER"),
                        new SimpleGrantedAuthority("ROLE_POSTMAN")));
    }

    @Test
    @WithMockUser
    public void convertUserAuthentication() throws Exception {
        Authentication authentication = authenticationConverter.extractAuthentication(map);

        Map<String, ?> aMap = authenticationConverter.convertUserAuthentication(authentication);

        assertThat("user_id should match that from input",
                aMap.get("user_id").toString(), is(map.get("user_id")));
    }

}
