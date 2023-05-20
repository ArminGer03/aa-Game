package view.Contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginMenuGController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label messageLabel;
    @FXML
    private Button loginButton ;



    public void signUp(MouseEvent mouseEvent) {
        //todo change menu
    }

    public void login(MouseEvent mouseEvent) {
        //todo check login via controller
    }
}
