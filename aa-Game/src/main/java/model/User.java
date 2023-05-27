package model;

import javafx.scene.paint.Color;
import utility.DataClass;
import utility.DataLoader;
import utility.RandomGenerator;
import utility.SHA;
import com.fasterxml.jackson.databind.ObjectMapper;


public class User {
    private String username;
    private String password;
    private String email;
    private String imagePath;
    private int imageNumber;
    private String gameMode;
    private String soundMode;
    private Color colorForCircle;
    private String color;

    public User(String username, String password, String email) {
        this.username = username;
        setPassword(password);
        this.email = email;
        DataClass.addUser(this);
        this.imageNumber = RandomGenerator.randomNumber(0,3);
        this.imagePath = RandomGenerator.randomImagePath(this.imageNumber);
        this.gameMode = "Easy";
        this.soundMode = "UnMute";
        this.colorForCircle = Color.BLACK;
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

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImage(String imagePath,int imageNumber) {
        this.imagePath = imagePath;
        this.imageNumber = imageNumber;
        DataLoader.saveUsers();
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getSoundMode() {
        return soundMode;
    }

    public void setSoundMode(String soundMode) {
        this.soundMode = soundMode;
    }

    public Color getColorForCircle() {
        return colorForCircle;
    }

    public void setColorForCircle(Color colorForCircle) {
        ObjectMapper objectMapper = new ObjectMapper();
        this.colorForCircle = colorForCircle;
        this.color = objectMapper.writeValueAsString(color);
    }
}
