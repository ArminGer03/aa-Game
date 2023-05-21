package model;

import utility.SHA;

import java.lang.reflect.Constructor;

public class User {
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        setPassword(password);
        this.email = email;
    }

    private void setPassword(String password) {
        this.password = SHA.shaString(password);
    }
    public boolean checkPassword(String passwordToCheck) {
        return SHA.shaString(passwordToCheck).equals(password);
    }
}
