package com.devbugger.spring.oauth2.user;

import com.devbugger.spring.oauth2.mocks.DataMock;
import com.devbugger.spring.oauth2.mocks.MockLocalUser;
import com.devbugger.spring.oauth2.mocks.UserServiceMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LocalUserServiceTest {

    @Test
    public void findUser() {
        LocalUserService<MockLocalUser> userService = new UserServiceMock();
        System.out.println(userService.findById(DataMock.idNat));
    }
}
