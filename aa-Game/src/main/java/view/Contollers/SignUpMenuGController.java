package view.Contollers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import utility.DataClass;
import utility.Regexes;
import view.LoginMenu;
import view.SignUpMenu;

public class SignUpMenuGController {
    @FXML
    private TextField username;
    @FXML
    private Label userMessage;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPass;
    @FXML
    private TextField email;


    @FXML
    public void initialize() {
        username.textProperty().addListener((observable, oldText, newText)->{
            if (newText.length() < 6){
                userMessage.setText("Username must be bigger than 5 characters!");
            }
            else if (Regexes.getMatcher(newText, Regexes.USER_FORMAT) != null){
                userMessage.setText("Username must contain only letters and numbers!");
            }
            else
                userMessage.setText(null);
        });
    }

    public void signUp(MouseEvent mouseEvent) {

    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(DataClass.getStage());
    }
}
