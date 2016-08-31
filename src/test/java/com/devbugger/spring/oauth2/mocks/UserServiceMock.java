package com.devbugger.spring.oauth2.mocks;

import com.devbugger.spring.oauth2.user.LocalUserService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Simple mock implementation of a{@link LocalUserService} used to
 * interact with actual implementations found in implementing applications.
 *
 * This mock is useful for generating the demo data necessary to pretend
 * we have an actual datastore backing the application, as we would in real life.
 */
public final class UserServiceMock implements LocalUserService<MockLocalUser> {

    private Set<MockLocalUser> dataStore;

    public UserServiceMock() {
        this.dataStore = new DataMock().populate().getDataStore();
    }

    @Override
    public MockLocalUser findById(UUID id) {
        for(MockLocalUser user: dataStore) {
            if(user.getId().equals(id))
                return user;
        }

        throw new NullPointerException("User not found in mock datastore using id " + id);
    }

    @Override
    public MockLocalUser findByUsername(String username) {
        for(MockLocalUser user: dataStore) {
            if(user.getUsername().equals(username))
                return user;
        }

        throw new NullPointerException("User not found in mock datastore using username " + username);
    }

    @Override
    public void initializeUser(OAuth2Authentication oAuth2Authentication) {

    }

    @Override
    public MockLocalUser initializeFromJWT(Map<String, ?> requestMap) {
        //implementors should implement this method on their own
        return (MockLocalUser) dataStore.toArray()[0];
    }
}
