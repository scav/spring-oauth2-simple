package com.devbugger.spring.oauth2.mocks;

import java.util.*;

public class DataMock {

    public static final UUID idPat =
            UUID.fromString("83d4c034-4ff9-11e5-885d-feff819cdc9f");
    public static final UUID idNat =
            UUID.fromString("83d4c034-4ff9-11e5-885d-feff819cdc9c");

    private Set<MockLocalUser> dataStore = new HashSet<MockLocalUser>();
    private Map<String, Object> map = new LinkedHashMap<String, Object>();

    public DataMock generateMap() {
        map.put("user_email", "pat@postman.org");
        map.put("user_id", idPat.toString());
        map.put("user_firstname", "Pat");
        map.put("user_surname", "Postman");
        map.put("scope", Collections.singletonList("write"));
        map.put("authorities", Arrays.asList("ROLE_TESTER", "ROLE_USER", "ROLE_POSTMAN"));
        map.put("exp", 1470969899L);
        map.put("jti", "5c0fe75d-30fa-4e9a-a9bf-a23d7dcdf223");
        map.put("client_id", "test");

        return this;
    }

    public DataMock populate() {
        MockLocalUser user = new MockLocalUser();
        user.setId(idPat);
        user.setUsername("pmanpat");
        user.setEmail("pat@postman.org");
        user.setFirsName("Pat");
        user.setSurname("Postman");
        user.setGalleryId("1000");
        dataStore.add(user);

        user = new MockLocalUser();
        user.setId(idNat);
        user.setUsername("pmannat");
        user.setEmail("nat@qostman.org");
        user.setFirsName("Nat");
        user.setSurname("Qostman");
        user.setGalleryId("1000");
        dataStore.add(user);

        return this;
    }

    public Set<MockLocalUser> getDataStore() {
        return dataStore;
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
