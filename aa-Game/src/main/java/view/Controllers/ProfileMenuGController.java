package view.Controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import utility.DataClass;

import java.io.File;

public class ProfileMenuGController {
    @FXML
    private ImageView profileImageView;

    public void getImage(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Image");
        File selectedFile = fileChooser.showOpenDialog(DataClass.getStage());
        if (selectedFile != null) {
            Image profileImage = new Image(selectedFile.toURI().toString());
            profileImageView.setImage(profileImage);
        }
    }
}
