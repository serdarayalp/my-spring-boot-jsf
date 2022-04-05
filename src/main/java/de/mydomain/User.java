package de.mydomain;

import java.util.Set;

public class User {

    private final String username;
    private final String emailId;
    private final String lastname;
    private final String firstname;
    private final String realmName;

    private final Set<String> roles;

    public User(String username, String emailId, String lastname,
                String firstname, String realmName, Set<String> roles) {

        this.username = username;
        this.emailId = emailId;
        this.lastname = lastname;
        this.firstname = firstname;
        this.realmName = realmName;

        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getFullname() {
        return firstname + " " + lastname;
    }

    public String getRealmName() {
        return realmName;
    }

    public Set<String> getRoles() {
        return roles;
    }
}

