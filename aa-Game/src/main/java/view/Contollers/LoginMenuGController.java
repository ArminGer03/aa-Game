package view.Contollers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginMenuGController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void submit(MouseEvent mouseEvent) {
        System.out.println(username.getText() + "    " + password.getText());
    }
}
