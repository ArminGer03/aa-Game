package view.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import utility.DataClass;

public class SettingMenuGController {
    @FXML
    private ChoiceBox<String> modeChoice;
    @FXML
    private ChoiceBox<String> songChoice;
    @FXML
    private ChoiceBox<String> langChoice;
    @FXML
    private ChoiceBox<String> ballsChoice;
    @FXML
    private ChoiceBox<String> themeChoice;
    @FXML
    private ChoiceBox<String> shootKeyChoice;
    @FXML
    private ChoiceBox<String> leftKeyChoice;
    @FXML
    private ChoiceBox<String> rightKeyChoice;
    @FXML
    private ChoiceBox<String> musicChoice;
    @FXML
    private ColorPicker colorChoice;


    ObservableList<String> modeChoicesList = FXCollections.observableArrayList("Easy", "Medium","Hard");
    ObservableList<String> songChoiceList = FXCollections.observableArrayList("Mute", "UnMute");
    ObservableList<String> langChoiceList = FXCollections.observableArrayList("English", "Persian");
    ObservableList<String> ballsChoiceList = FXCollections.observableArrayList("10", "11","12","13","14","15");
    ObservableList<String> themeChoiceList = FXCollections.observableArrayList("Normal", "B&W");
    ObservableList<String> musicChoiceList = FXCollections.observableArrayList("evolution", "jazzyfrenchy","summer");
    ObservableList<String> shootChoiceList = FXCollections.observableArrayList("Space", "S","J","Enter");
    ObservableList<String> leftChoiceList = FXCollections.observableArrayList("Left", "A","H");
    ObservableList<String> rightChoiceList = FXCollections.observableArrayList("Right", "D","K");


    @FXML
    private void initialize(){
         modeChoice.setItems(modeChoicesList);
         songChoice.setItems(songChoiceList);
         langChoice.setItems(langChoiceList);
         ballsChoice.setItems(ballsChoiceList);
         themeChoice.setItems(themeChoiceList);
         musicChoice.setItems(musicChoiceList);
         shootKeyChoice.setItems(shootChoiceList);
         leftKeyChoice.setItems(leftChoiceList);
         rightKeyChoice.setItems(rightChoiceList);
         modeChoice.setValue(DataClass.getCurrentUser().getGameMode());
         songChoice.setValue(DataClass.getCurrentUser().getSoundMode());
         langChoice.setValue(DataClass.getCurrentUser().getLanguage());
         ballsChoice.setValue(Integer.toString(DataClass.getCurrentUser().getBalls()));
         colorChoice.setValue(DataClass.getCurrentUser().getColor());
         themeChoice.setValue(DataClass.getCurrentUser().getTheme());
         musicChoice.setValue(DataClass.getCurrentUser().getSoundTrackPath());
         shootKeyChoice.setValue(DataClass.getCurrentUser().getShootKey());
         leftKeyChoice.setValue(DataClass.getCurrentUser().getLeftKey());
         rightKeyChoice.setValue(DataClass.getCurrentUser().getRightKey());
    }


    public void changeDifficulty(MouseEvent mouseEvent) {
        modeChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            DataClass.getCurrentUser().setGameMode(modeChoice.getValue());
        });
    }

    public void changeSound(MouseEvent mouseEvent) {
        songChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            DataClass.getCurrentUser().setSoundMode(songChoice.getValue());
        });
    }

    public void changeLanguage(MouseEvent mouseEvent) {
        langChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            DataClass.getCurrentUser().setLanguage(langChoice.getValue());
        });
    }

    public void changeColor(ActionEvent actionEvent) {
        colorChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            DataClass.getCurrentUser().setColorHex(colorChoice.getValue());
        });
    }

    public void changeBalls(MouseEvent mouseEvent) {
        ballsChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            DataClass.getCurrentUser().setBalls(Integer.parseInt(ballsChoice.getValue()));
        });
    }

    public void changeTheme(MouseEvent mouseEvent) {
        themeChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            DataClass.getCurrentUser().setTheme(themeChoice.getValue());
        });
    }

    public void changeShootKey(MouseEvent mouseEvent) {
        shootKeyChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            DataClass.getCurrentUser().setShootKey(shootKeyChoice.getValue());
        });
    }

    public void changeLeftKey(MouseEvent mouseEvent) {
        leftKeyChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            DataClass.getCurrentUser().setLeftKey(leftKeyChoice.getValue());
        });
    }

    public void changeRightKey(MouseEvent mouseEvent) {
        rightKeyChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            DataClass.getCurrentUser().setRightKey(rightKeyChoice.getValue());
        });
    }

    public void changeMusic(MouseEvent mouseEvent) {
        musicChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            DataClass.getCurrentUser().setSoundTrackPath(musicChoice.getValue());
        });
    }
}
