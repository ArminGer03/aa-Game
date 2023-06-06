package view.Controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import utility.DataClass;
import view.Game;

import java.net.URISyntaxException;
import java.util.Optional;

public class PauseMenuGController {

    @FXML
    private ChoiceBox<String> modeChoice;
    @FXML
    private ChoiceBox<String> songChoice;
    @FXML
    private ChoiceBox<String> musicChoice;
    @FXML
    private ChoiceBox<String> shootKeyChoice;
    @FXML
    private ChoiceBox<String> leftKeyChoice;
    @FXML
    private ChoiceBox<String> rightKeyChoice;

    ObservableList<String> shootChoiceList = FXCollections.observableArrayList("Space","W", "U", "S","J","Enter");
    ObservableList<String> leftChoiceList = FXCollections.observableArrayList("Left", "A","H");
    ObservableList<String> rightChoiceList = FXCollections.observableArrayList("Right", "D","K");
    ObservableList<String> musicChoiceList = FXCollections.observableArrayList("evolution", "jazzyfrenchy","summer");
    ObservableList<String> modeChoicesList = FXCollections.observableArrayList("Easy", "Medium","Hard");
    ObservableList<String> songChoiceList = FXCollections.observableArrayList("Mute", "UnMute");

    @FXML
    private void initialize(){
        modeChoice.setItems(modeChoicesList);
        songChoice.setItems(songChoiceList);
        musicChoice.setItems(musicChoiceList);
        shootKeyChoice.setItems(shootChoiceList);
        leftKeyChoice.setItems(leftChoiceList);
        rightKeyChoice.setItems(rightChoiceList);

        modeChoice.setValue(DataClass.getCurrentUser().getGameMode());
        songChoice.setValue(DataClass.getCurrentUser().getSoundMode());
        musicChoice.setValue(DataClass.getCurrentUser().getSoundTrackPath());
        shootKeyChoice.setValue(DataClass.getCurrentUser().getShootKey());
        leftKeyChoice.setValue(DataClass.getCurrentUser().getLeftKey());
        rightKeyChoice.setValue(DataClass.getCurrentUser().getRightKey());
    }


    public void changeDifficulty() {
        modeChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            DataClass.getCurrentUser().setGameMode(modeChoice.getValue());
            Game.setGameDifficulty();
        });
    }

    public void changeSound() {
        songChoice.valueProperty().addListener((observable, oldValue, newValue) ->
                DataClass.getCurrentUser().setSoundMode(songChoice.getValue()));
    }

    public void changeShootKey() {
        shootKeyChoice.valueProperty().addListener((observable, oldValue, newValue) ->
                DataClass.getCurrentUser().setShootKey(shootKeyChoice.getValue()));
    }

    public void changeLeftKey() {
        leftKeyChoice.valueProperty().addListener((observable, oldValue, newValue) ->
                DataClass.getCurrentUser().setLeftKey(leftKeyChoice.getValue()));
    }

    public void changeRightKey() {
        rightKeyChoice.valueProperty().addListener((observable, oldValue, newValue) ->
                DataClass.getCurrentUser().setRightKey(rightKeyChoice.getValue()));
    }

    public void changeMusic() {
        musicChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            DataClass.getCurrentUser().setSoundTrackPath(musicChoice.getValue());
            try {
                Game.changeMusic();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void restartGame()  {
    }

    public void resumeGame() {
        DataClass.getPauseStage().close();
        Game.playGame();
    }

    public void exitGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Program");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Any unsaved data would be lost.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            DataClass.setCurrentUser(null);
            Platform.exit();
        }
    }
}
