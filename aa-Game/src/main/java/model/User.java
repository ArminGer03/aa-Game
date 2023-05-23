package model;

import utility.DataClass;
import utility.DataLoader;
import utility.RandomGenerator;
import utility.SHA;

import java.lang.reflect.Constructor;

public class User {
    private String username;
    private String password;
    private String email;
    private String imagePath;

    public User(String username, String password, String email) {
        this.username = username;
        setPassword(password);
        this.email = email;
        DataClass.addUser(this);
        this.imagePath = RandomGenerator.randomImagePath();
        DataLoader.saveUsers();
    }

    public void setPassword(String password) {
        this.password = SHA.shaString(password);
        DataLoader.saveUsers();
    }
    public boolean checkPassword(String passwordToCheck) {
        return SHA.shaString(passwordToCheck).equals(password);
    }

    public String getEmail() {
        return email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        DataLoader.saveUsers();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        DataLoader.saveUsers();
    }
}
