package com.devbugger.spring.oauth2;

import com.devbugger.spring.oauth2.mocks.UserServiceMock;
import com.devbugger.spring.oauth2.user.LocalUserDetailsService;
import com.devbugger.spring.oauth2.user.LocalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Simple test context bringing up the dependencies that should be
 * available through injection for all test classes.
 */
@Configuration
public class TestContext {

    @Autowired
    private LocalUserService localUserService;

    @Bean
    public LocalUserDetailsService userDetailsService() {
        return new LocalUserDetailsService(localUserService);
    }

    @Bean
    public LocalUserService localUserService() {
        return new UserServiceMock();
    }


}
