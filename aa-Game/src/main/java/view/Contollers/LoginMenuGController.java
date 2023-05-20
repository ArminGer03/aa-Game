package view.Contollers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utility.DataClass;
import view.SignUpMenu;

public class LoginMenuGController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label messageLabel;
    @FXML
    private Button loginButton ;



    public void signUp(MouseEvent mouseEvent) throws Exception {
        new SignUpMenu().start(DataClass.getStage());
    }

    public void login(MouseEvent mouseEvent) {
        //todo check login via controller
    }

    public void quickGame(MouseEvent mouseEvent) {
        //todo make a quick game
    }
}
