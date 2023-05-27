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
    private ColorPicker colorChoice;


    ObservableList<String> modeChoicesList = FXCollections.observableArrayList("Easy", "Medium","Hard");
    ObservableList<String> songChoiceList = FXCollections.observableArrayList("Mute", "UnMute");
    ObservableList<String> langChoiceList = FXCollections.observableArrayList("English", "Persian");


    @FXML
    private void initialize(){
         modeChoice.setItems(modeChoicesList);
         songChoice.setItems(songChoiceList);
         langChoice.setItems(langChoiceList);
         modeChoice.setValue(DataClass.getCurrentUser().getGameMode());
         songChoice.setValue(DataClass.getCurrentUser().getSoundMode());
         langChoice.setValue("English");
         colorChoice.setValue(DataClass.getCurrentUser().getColor());
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
}
