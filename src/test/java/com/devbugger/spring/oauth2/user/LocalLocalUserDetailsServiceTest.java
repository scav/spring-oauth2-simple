package com.devbugger.spring.oauth2.user;

import com.devbugger.spring.oauth2.mocks.MockLocalUser;
import com.devbugger.spring.oauth2.mocks.UserServiceMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(JUnit4.class)
public class LocalLocalUserDetailsServiceTest {

    private LocalUserDetailsService userDetailsService;

    @Before
    public void setup() {
        LocalUserService<MockLocalUser> userService = new UserServiceMock();
        userDetailsService = new LocalUserDetailsService(userService);
    }

    @Test
    public void loadUserByUsername() throws Exception {
        LocalUserDetails userDetails =
                userDetailsService.loadUserByUsername("pmannat");

        System.out.println(userDetails.getUser());

        assertThat("Class is instance of MockLocalUser",
                userDetails.getUser(), instanceOf(MockLocalUser.class));
    }
}
