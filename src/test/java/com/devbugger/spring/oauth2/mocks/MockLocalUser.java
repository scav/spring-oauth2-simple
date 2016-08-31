package com.devbugger.spring.oauth2.mocks;

import com.devbugger.spring.oauth2.user.LocalUser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represent an actual {@link LocalUser} implemented by an actual
 * system.
 *
 * Gallery? It had to be something unique for a possible service.
 */
public class MockLocalUser implements LocalUser {

    private UUID id;

    private String username;

    private String email;

    private String firsName;

    private String surname;

    private List<String> authorities = new ArrayList<String>();

    private String galleryId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(String galleryId) {
        this.galleryId = galleryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MockLocalUser)) return false;

        MockLocalUser that = (MockLocalUser) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firsName != null ? !firsName.equals(that.firsName) : that.firsName != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (authorities != null ? !authorities.equals(that.authorities) : that.authorities != null) return false;
        return galleryId != null ? galleryId.equals(that.galleryId) : that.galleryId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firsName != null ? firsName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (authorities != null ? authorities.hashCode() : 0);
        result = 31 * result + (galleryId != null ? galleryId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MockLocalUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firsName='" + firsName + '\'' +
                ", surname='" + surname + '\'' +
                ", authorities=" + authorities +
                ", galleryId='" + galleryId + '\'' +
                "} " + super.toString();
    }
}
