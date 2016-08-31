package com.devbugger.spring.oauth2.test;

import com.devbugger.spring.oauth2.user.LocalUserDetails;
import com.devbugger.spring.oauth2.user.LocalUserDetailsService;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.*;

/**
 * Emulate running with a {@link LocalUserDetails} returned from the
 * {@link LocalUserDetailsService}. This is useful when you need to
 * set up a user to test methods that take a certain username or role level.
 *
 * @author Dag Østgulen Heradstveit.
 */


@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@WithSecurityContext(factory = WithOauthDetailsSecurityContextFactory.class)
public @interface WithOauthDetails {

    /**
     * Username to use for authorization.
     *
     * @return
     */
    String username() default "user";

    /**
     * List of all authorities for this user.
     *
     * @return
     */
    String[] authorities() default {};
}
