package view.Contollers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utility.DataClass;
import view.ForgotMenu;
import view.LoginMenu;
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



    public void signUp(MouseEvent mouseEvent) throws Exception {
        new SignUpMenu().start(DataClass.getStage());
    }

    public void login(MouseEvent mouseEvent) throws Exception {
        //todo check login
    }

    public void quickGame(MouseEvent mouseEvent) {
        //todo make a quick game
    }

    public void forgotPassword(MouseEvent mouseEvent) throws Exception {
        new ForgotMenu().start(DataClass.getStage());
    }
}
