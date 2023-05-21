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
    private Label passwordMessage;
    @FXML
    private PasswordField confirmPass;
    @FXML
    private TextField email;
    @FXML
    private Label emailMessage;


    @FXML
    public void initialize() {
        //username label message:
        username.textProperty().addListener((observable, oldText, newText)->{
            if (newText.length() < 6){
                userMessage.setText("Username must be bigger than 5 characters!");
            }
            else if (Regexes.getMatcher(newText, Regexes.USER_FORMAT) != null){
                userMessage.setText("Username must contain only letters and numbers!");
            }
            //taken username
            else
                userMessage.setText(null);
        });
        //password label message:
        password.textProperty().addListener((observable, oldText, newText)->{
            if (newText.length() < 6){
                passwordMessage.setText("Password must be bigger than 5 characters!");
            }
            else if (Regexes.getMatcher(newText, Regexes.PASS_UPPER) == null){
                passwordMessage.setText("Password must contain at least one uppercase letter!");
            }
            else if (Regexes.getMatcher(newText, Regexes.PASS_LOWER) == null){
                passwordMessage.setText("Password must contain at least one lowercase letter!");
            }
            else if (Regexes.getMatcher(newText, Regexes.PASS_DIGIT) == null){
                passwordMessage.setText("Password must contain at least one number!");
            }
            else if (Regexes.getMatcher(newText, Regexes.PASS_NO_SPACE) == null){
                passwordMessage.setText("Password cant have spaces!");
            }
            else
                passwordMessage.setText(null);
        });
        //email label message:
        email.textProperty().addListener((observable, oldText, newText)->{
            if (Regexes.getMatcher(newText, Regexes.EMAIL_FORMAT) == null){
                emailMessage.setText("Invalid email format!");
            }
            else
                emailMessage.setText(null);
        });
    }

    public void signUp(MouseEvent mouseEvent) {

    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(DataClass.getStage());
    }
}
