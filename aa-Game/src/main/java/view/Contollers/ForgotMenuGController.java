package view.Contollers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.User;
import utility.DataClass;
import utility.Regexes;
import view.LoginMenu;

public class ForgotMenuGController {
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Label userLabel;
    @FXML
    private Label passLabel;
    @FXML
    private Button changeButton ;
    @FXML
    private Button backButton ;

    @FXML
    public void initialize() {

        newPassword.setVisible(false);
        confirmPassword.setVisible(false);


        //username label message:
        username.textProperty().addListener((observable, oldText, newText)->{
            if (DataClass.getUserByUsername(newText) == null){
                userLabel.setText("This user doesn't exist!");
                newPassword.setVisible(false);
                confirmPassword.setVisible(false);
            }
            else{
                if(email.getText().isEmpty()){
                    userLabel.setText("Fill email field!");
                    newPassword.setVisible(false);
                    confirmPassword.setVisible(false);
                }
                else if (DataClass.getUserByEmail(email.getText()).getUsername().equals(username.getText())) {
                    userLabel.setText(null);
                    newPassword.setVisible(true);
                    confirmPassword.setVisible(true);
                }
            }
        });
        email.textProperty().addListener((observable, oldText, newText)->{
            if (DataClass.getUserByEmail(newText) == null){
                userLabel.setText("This email doesn't exist!");
                newPassword.setVisible(false);
                confirmPassword.setVisible(false);
            }
            else if (!DataClass.getUserByEmail(newText).getUsername().equals(username.getText())) {
                userLabel.setText("Email doesn't match with user!");
                newPassword.setVisible(false);
                confirmPassword.setVisible(false);
            }
            else{
                userLabel.setText(null);
                newPassword.setVisible(true);
                confirmPassword.setVisible(true);
            }
        });

        //password label message:
        newPassword.textProperty().addListener((observable, oldText, newText)->{
            if (newText.length() < 6){
                passLabel.setText("Password must be bigger than 5 characters!");
            }
            else if (Regexes.getMatcher(newText, Regexes.PASS_UPPER) == null){
                passLabel.setText("Password must contain at least one uppercase letter!");
            }
            else if (Regexes.getMatcher(newText, Regexes.PASS_LOWER) == null){
                passLabel.setText("Password must contain at least one lowercase letter!");
            }
            else if (Regexes.getMatcher(newText, Regexes.PASS_DIGIT) == null){
                passLabel.setText("Password must contain at least one number!");
            }
            else if (Regexes.getMatcher(newText, Regexes.PASS_NO_SPACE) == null){
                passLabel.setText("Password cant have spaces!");
            }
            else
                passLabel.setText(null);
        });
        confirmPassword.textProperty().addListener((observable, oldText, newText)->{
            if (confirmPassword.getText().isEmpty()){
                passLabel.setText("Confirm password is empty!");
            }
            else if (!confirmPassword.getText().equals(newPassword.getText())){
                passLabel.setText("Confirm password doesn't match with password!");
            }
            else
                passLabel.setText(null);
        });

    }


    public void changePass(MouseEvent mouseEvent) throws Exception {
        if (username.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Forgot password Error");
            alert.setContentText("Your username field is empty");
            alert.showAndWait();
        }
        else if (email.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Forgot password Error");
            alert.setContentText("Your email field is empty");
            alert.showAndWait();
        }
        else if (newPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Forgot password Error");
            alert.setContentText("Your new password field is empty");
            alert.showAndWait();
        }
        else if (confirmPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Forgot password Error");
            alert.setContentText("Your confirm password field is empty");
            alert.showAndWait();
        }
        else if ((userLabel.getText() != null) || (passLabel.getText() != null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Forgot password Error");
            alert.setContentText("One of the fields are invalid");
            alert.showAndWait();
        }
        else {
            User user = DataClass.getUserByUsername(username.getText());
            user.setPassword(newPassword.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful!");
            alert.setHeaderText("Changed password");
            alert.setContentText("Your password was changed successfully");
            alert.showAndWait();
            new LoginMenu().start(DataClass.getStage());
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(DataClass.getStage());
    }
}
