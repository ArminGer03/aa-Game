package view.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.User;
import utility.DataClass;
import view.ForgotMenu;
import view.Game;
import view.MainMenu;
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
    @FXML
    private Hyperlink forgotPass;


    @FXML
    public void initialize() {
        username.textProperty().addListener((observable, oldText, newText) -> {
            if (DataClass.getUserByUsername(newText) == null) {
                messageLabel.setText("Username doesn't exist!");
            }
            else{
                if(password.getText().isEmpty()){
                    messageLabel.setText("Fill password field!");
                }
                else
                    messageLabel.setText(null);
            }
        });
        password.textProperty().addListener((observable, oldText, newText) -> {
            if(username.getText().isEmpty()){
                messageLabel.setText("Fill username field!");
            }
            else if (DataClass.getUserByUsername(username.getText()) == null) {
                messageLabel.setText("Username doesn't exist!");
            }
            else
                messageLabel.setText(null);
        });
    }

    public void signUp(MouseEvent mouseEvent) throws Exception {
        new SignUpMenu().start(DataClass.getStage());
    }

    public void login(MouseEvent mouseEvent) throws Exception {
        if (username.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Login Error");
            alert.setContentText("Your username field is empty");
            alert.showAndWait();
        }
        else if (password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Login Error");
            alert.setContentText("Your password field is empty");
            alert.showAndWait();
        }
        else if (messageLabel.getText() != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Login Error");
            alert.setContentText("One of the fields are invalid");
            alert.showAndWait();
        }
        else if (!DataClass.getUserByUsername(username.getText()).checkPassword(password.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Login Error");
            alert.setContentText("Incorrect password, naaghola!");
            alert.showAndWait();
        }
        else {
            User user = DataClass.getUserByUsername(username.getText());
            DataClass.setCurrentUser(user);
            new MainMenu().start(DataClass.getStage());
        }
    }

    public void quickGame(MouseEvent mouseEvent) throws Exception {
        User user = DataClass.getUserByUsername("Guest");
        DataClass.setCurrentUser(user);
        new Game().start(DataClass.getStage());
    }

    public void forgotPassword(MouseEvent mouseEvent) throws Exception {
        new ForgotMenu().start(DataClass.getStage());
    }
}
