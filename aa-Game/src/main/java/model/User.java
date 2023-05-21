package model;

import utility.DataClass;
import utility.DataLoader;
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
        DataClass.addUser(this);
        DataLoader.saveUsers();
    }

    private void setPassword(String password) {
        this.password = SHA.shaString(password);
    }
    public boolean checkPassword(String passwordToCheck) {
        return SHA.shaString(passwordToCheck).equals(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
