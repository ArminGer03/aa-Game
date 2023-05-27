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

    public User(String username, String password, String email) throws JsonProcessingException {
        this.username = username;
        setPassword(password);
        this.email = email;
        DataClass.addUser(this);
        this.imageNumber = RandomGenerator.randomNumber(0,3);
        this.imagePath = RandomGenerator.randomImagePath(this.imageNumber);
        this.gameMode = "Easy";
        this.soundMode = "UnMute";
        setColorHex(Color.BEIGE);
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
    }

    public String getSoundMode() {
        return soundMode;
    }

    public void setSoundMode(String soundMode) {
        this.soundMode = soundMode;
    }

    public Color getColor() {
        return ColorConverter.hexToColor(colorHex);
    }

    public void setColorHex(Color color) {
        this.colorHex = ColorConverter.colorToHex(color);
    }

    //todo delete this shit
    public static void main(String[] args) throws JsonProcessingException {
        new User("Armin","test","test");
    }
}
