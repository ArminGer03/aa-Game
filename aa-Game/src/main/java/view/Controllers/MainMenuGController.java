package view.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import view.MainMenu;
import view.SignUpMenu;

import java.io.IOException;
import java.net.URL;

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

    public void settingsMenu(MouseEvent mouseEvent) {
    }

    public void scoreBoard(MouseEvent mouseEvent) {
    }

    public void exit(MouseEvent mouseEvent) {
    }

    public void startGame(MouseEvent mouseEvent) {
    }

    public void resumeGame(MouseEvent mouseEvent) {
    }

    public void start1v1Game(MouseEvent mouseEvent) {
    }
}
