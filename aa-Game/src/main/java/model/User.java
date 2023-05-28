package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.scene.paint.Color;
import utility.*;


public class User {
    private String username;
    private String password;
    private String email;
    private String imagePath;
    private int imageNumber;
    private String gameMode;
    private String soundMode;
    private String colorHex;
    private String language;
    private int balls;
    private String soundTrackPath;

    public User(String username, String password, String email) throws JsonProcessingException {
        this.username = username;
        setPassword(password);
        this.email = email;
        DataClass.addUser(this);
        this.imageNumber = RandomGenerator.randomNumber(0,3);
        this.imagePath = RandomGenerator.randomImagePath(this.imageNumber);
        this.gameMode = "Easy";
        this.soundMode = "UnMute";
        this.language = "English";
        setColorHex(Color.BEIGE);
        this.balls = 10;
        soundTrackPath = "agha reza";
        Loader.saveUsers();
    }

    public void setPassword(String password) {
        this.password = SHA.shaString(password);
        Loader.saveUsers();
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
        Loader.saveUsers();
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
        Loader.saveUsers();
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
        Loader.saveUsers();
    }

    public String getSoundMode() {
        return soundMode;
    }

    public void setSoundMode(String soundMode) {
        this.soundMode = soundMode;
        Loader.saveUsers();
    }

    public Color getColor() {
        return ColorConverter.hexToColor(colorHex);
    }

    public void setColorHex(Color color) {
        this.colorHex = ColorConverter.colorToHex(color);
        Loader.saveUsers();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
        Loader.saveUsers();
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
        Loader.saveUsers();
    }

    public String getSoundTrackPath() {
        return soundTrackPath;
    }

    public void setSoundTrackPath(String soundTrackPath) {
        this.soundTrackPath = soundTrackPath;
    }

    //todo delete this shit
    public static void main(String[] args) throws JsonProcessingException {
        new User("a","a","a");
    }
}
