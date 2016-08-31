# Spring Oauth2 Simple
A simple utility implementation for Spring based applications running Spring Security.
It makes a lot of assumptions on how you set up you services, experimenting is advised.
Prior knowledge of Spring Security, Spring Boot and JUnit with Spring Testing is expected. Knowledge of Oauth2 is also useful.   
*Note that this library is only useful if you intend to use it* ***as is***.

### What does it do
1. Provides a simple pre-configured infrastructure for user management, and it expects this to be enforced
throughout the application.
2. An easy way of plugging into, and testing, Oauth2 authenticated users in test classes. This is useful if user roles are not kept locally on the service, but centralized at a login server where it is possible for owners of a service to define different roles for each user in a system.

## Using the library 
First compile it to at least Java version 1.6.    
The library expect all users to have their ID of type UUID. If this is not what you need, you are free to work around this.
**TL;DR USERS MUST HAVE ID OF TYPE UUID**

### Application configuration
Start by defining your resource server configuration (some code is omitted).
```java 
@Configuration
@EnableResourceServer
public class Oauth2ResourceServer extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.jwt.keyValue}")
    private String pubKey;

    @Autowired
    private UserService userService;

    @Autowired
    private LocalUserDetailsService localUserDetailsService;

    @Bean
    public LocalUserDetailsService localUserDetailsService() {
        return new LocalUserDetailsService(userService);
    }
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(tokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter tokenConverter() {
        LocalTokenConverter localTokenConverter = new LocalTokenConverter();
        localTokenConverter.setPubKey(pubKey);
        localTokenConverter.setUserDetailsService(localUserDetailsService);

        return localTokenConverter.tokenEnhancer();
    }
    
    @Bean
    public ResourceServerTokenServices jwtTokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenEnhancer(tokenConverter());
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }
}
```

Let your applications User class implement the LocalUser interface.
```java 
public class User implements LocalUser {
    private UUID id;
    private String username;
}
```

Let your UserService interface extend the provided UserService interface.
Note that LocalUserService will accept any class of extending LocalUser.
        
```java 
public interface UserService implements LocalUserService<T> {
}
```

### Test instance
Only one test helper is provided, it is inspired by the standard @WithUserDetails from spring-security-testing.
Following is an example for testing with Spring Boot 1.3 (has only been built and tested with 1.3).
This test will provide you with a SecurityContext populated by a User class with the username "admin@example.org" and the roles "ROLE_ADMIN" and "ROLE_USER".

```java 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MyApplication.class)
public abstract class AbstractTest {

    @Test
    @WithOauthDetails(username = "admin@example.org", authorities = {ROLE_ADMIN, ROLE_USER})
    public void asAdmin() throws Exception {
        //Test code needing admin rights.
    }
    @Test
    @WithOauthDetails(username = "user@example.org", authorities = {ROLE_USER})
    public void asUser() throws Exception {
        //Test code needing only user rights.
    }
}
```


























