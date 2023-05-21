package view.Contollers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.User;
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
        //confirm password label message:
        confirmPass.textProperty().addListener((observable, oldText, newText)->{
            if (confirmPass.getText().isEmpty()){
                passwordMessage.setText("Confirm password is empty!");
            }
            else if (!confirmPass.getText().equals(password.getText())){
                passwordMessage.setText("Confirm password doesn't match with password!");
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

    public void signUp(MouseEvent mouseEvent) throws Exception {

        if (username.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("SignUp Error");
            alert.setContentText("Your username field is empty");
            alert.showAndWait();
        }
        else if (password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("SignUp Error");
            alert.setContentText("Your password field is empty");
            alert.showAndWait();
        }
        else if (confirmPass.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("SignUp Error");
            alert.setContentText("Your confirm password field is empty");
            alert.showAndWait();
        }
        else if (email.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("SignUp Error");
            alert.setContentText("Your email field is empty");
            alert.showAndWait();
        }
        else if ((userMessage.getText() != null) || (passwordMessage.getText() != null) || (emailMessage.getText() != null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("SignUp Error");
            alert.setContentText("One of the fields are invalid");
            alert.showAndWait();
        }
        else {
            String Username = username.getText();
            String Password = password.getText();
            String Email = email.getText();
            new User(Username,Password,Email);
            System.out.println(DataClass.getUsers());
            //todo switch menu
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(DataClass.getStage());
    }
}
