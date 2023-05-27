package view.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import utility.DataClass;
import view.Game;
import view.MainMenu;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class MainMenuGController {

    @FXML
    private Label mainLabel;
    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane anchorPane;

    public void mainMenu(MouseEvent mouseEvent) {
        borderPane.setCenter(anchorPane);
    }

    public void profileMenu(MouseEvent mouseEvent) throws IOException {
        URL url = MainMenu.class.getResource("/fxml/ProfileMenu.fxml");
        Parent root = FXMLLoader.load(url);
        borderPane.setCenter(root);
    }

    public void settingsMenu(MouseEvent mouseEvent) throws IOException {
        URL url = MainMenu.class.getResource("/fxml/SettingMenu.fxml");
        Parent root = FXMLLoader.load(url);
        borderPane.setCenter(root);
    }

    public void scoreBoard(MouseEvent mouseEvent) {
        //todo scoreboard
    }

    public void exit(MouseEvent mouseEvent) {
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

    public void startGame(MouseEvent mouseEvent) throws Exception {
        new Game().start(DataClass.getStage());
    }

    public void resumeGame(MouseEvent mouseEvent) {
        //todo save game
    }

    public void start1v1Game(MouseEvent mouseEvent) {
        //todo 1v1
    }
}
