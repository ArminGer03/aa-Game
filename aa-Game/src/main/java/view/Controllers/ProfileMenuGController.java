package view.Controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.User;
import utility.DataClass;
import view.LoginMenu;

import java.io.File;
import java.net.URL;

public class ProfileMenuGController {
    User currentUser = DataClass.getCurrentUser();
    @FXML
    private ImageView profileImageView;

    @FXML
    public void initialize() {
        String path = String.valueOf(LoginMenu.class.getResource(currentUser.getImagePath()));
        Image avatar = new Image(path);
        profileImageView.setImage(avatar);
    }

    public void getImage(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Image");
        File selectedFile = fileChooser.showOpenDialog(DataClass.getStage());
        if (selectedFile != null) {
            Image profileImage = new Image(selectedFile.toURI().toString());
            String profileImagePath = selectedFile.getAbsolutePath();
            currentUser.setImagePath(profileImagePath);
            profileImageView.setImage(profileImage);
        }
    }
}
