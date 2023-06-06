package view.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import utility.DataClass;
import view.Game;
import view.MainMenu;
import view.ScoreBoardMenu;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class MainMenuGController {

    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane anchorPane;

    public void mainMenu() {
        borderPane.setCenter(anchorPane);
    }

    public void profileMenu() throws IOException {
        URL url = MainMenu.class.getResource("/fxml/ProfileMenu.fxml");
        assert url != null;
        Parent root = FXMLLoader.load(url);
        borderPane.setCenter(root);
    }

    public void settingsMenu() throws IOException {
        URL url = MainMenu.class.getResource("/fxml/SettingMenu.fxml");
        assert url != null;
        Parent root = FXMLLoader.load(url);
        borderPane.setCenter(root);
    }

    public void scoreBoard() throws Exception {
        new ScoreBoardMenu().start(DataClass.getStage());
    }

    public void exit() {
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

    public void startGame() throws Exception {
        new Game().start(DataClass.getStage());
    }

    public void resumeGame() throws Exception {
        new Game().start(DataClass.getStage());
    }

    public void start1v1Game() {
        //todo 1v1
    }
}
