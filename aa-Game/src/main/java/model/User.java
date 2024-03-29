package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.scene.paint.Color;
import utility.*;

import java.util.HashMap;


public class User {
    private String username;
    private String password;
    private String email;
    private String imagePath;
    private int imageNumber;
    private String gameMode;
    private String soundMode;
    private String colorHex;
    private String theme;
    private String language;
    private String shootKey;
    private String leftKey;
    private String rightKey;
    private int balls;
    private String soundTrackPath;
    private HashMap<String,Integer> HighScores;
    private HashMap<String,Integer> HighScoreFinishedTime;

    public User(String username, String password, String email) throws JsonProcessingException {
        this.username = username;
        setPassword(password);
        this.email = email;
        this.imageNumber = RandomGenerator.randomNumber(0,3);
        this.imagePath = RandomGenerator.randomImagePath(this.imageNumber);
        this.gameMode = "Medium";
        this.soundMode = "UnMute";
        this.language = "English";
        this.theme = "Normal";
        this.shootKey = "Space";
        this.leftKey = "Left";
        this.rightKey = "right";
        setColorHex(Color.BLACK);
        this.balls = 10;
        soundTrackPath = "evolution";
        HighScores = new HashMap<>();
        HighScoreFinishedTime = new HashMap<>();
        HighScores.put("Easy",0);
        HighScores.put("Medium",0);
        HighScores.put("Hard",0);
        HighScoreFinishedTime.put("Easy",-1);
        HighScoreFinishedTime.put("Medium",-1);
        HighScoreFinishedTime.put("Hard",-1);
        DataClass.addUser(this);
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
        Loader.saveUsers();
    }

    public String getSoundTrackPath() {
        return soundTrackPath;
    }

    public void setSoundTrackPath(String soundTrackPath) {
        this.soundTrackPath = soundTrackPath;
        Loader.saveUsers();
    }

    public String getShootKey() {
        return shootKey;
    }

    public void setShootKey(String shootKey) {
        this.shootKey = shootKey;
        Loader.saveUsers();
    }

    public String getLeftKey() {
        return leftKey;
    }

    public void setLeftKey(String leftKey) {
        this.leftKey = leftKey;
        Loader.saveUsers();
    }

    public String getRightKey() {
        return rightKey;
    }

    public void setRightKey(String rightKey) {
        this.rightKey = rightKey;
        Loader.saveUsers();
    }

    public HashMap<String, Integer> getHighScores() {
        return HighScores;
    }

    public HashMap<String, Integer> getHighScoreFinishedTime() {
        return HighScoreFinishedTime;
    }

    public boolean isUnMute(){
        return this.soundMode.equals("UnMute");
    }

    public Boolean isNormalTheme(){
        return this.theme.equals("Normal");
    }


}
