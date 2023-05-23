package view.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.User;
import utility.DataClass;
import utility.RandomGenerator;
import utility.Regexes;
import view.LoginMenu;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProfileMenuGController {
    User currentUser = DataClass.getCurrentUser();
    @FXML
    private ImageView profileImageView;
    @FXML
    private Label userLabel;
    @FXML
    private TextField newUsername;
    @FXML
    private CheckBox check0;
    @FXML
    private CheckBox check1;
    @FXML
    private CheckBox check2;
    @FXML
    private CheckBox check3;

    @FXML
    public void initialize() {
        //set username label
        userLabel.setText(currentUser.getUsername());
        //set avatar
        String path = String.valueOf(LoginMenu.class.getResource(currentUser.getImagePath()));
        Image avatar = new Image(path);
        profileImageView.setImage(avatar);

        //set available avatars
        switch (currentUser.getImageNumber()){
            case 0:
                check0.setVisible(false);
                break;
            case 1:
                check1.setVisible(false);
                break;
            case 2:
                check2.setVisible(false);
                break;
            case 3:
                check3.setVisible(false);
                break;
            default:
                check0.setVisible(true);
                check1.setVisible(true);
                check2.setVisible(true);
                check3.setVisible(true);
                break;
        }
        //user textField listener
        //username label message:
        newUsername.textProperty().addListener((observable, oldText, newText)->{
            if (newText.length() < 6){
                newUsername.setText("Username must be bigger than 5 characters!");
            }
            else if (Regexes.getMatcher(newText, Regexes.USER_FORMAT) != null){
                newUsername.setText("Username must contain only letters and numbers!");
            }
            else if (DataClass.getUserByUsername(newText) != null){
                newUsername.setText("Username already taken!");
            }
            else
                newUsername.setText(null);
        });
    }

    public void getImage(MouseEvent mouseEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Image");
        File selectedFile = fileChooser.showOpenDialog(DataClass.getStage());
        if (selectedFile != null) {
            //copy file
            String profileImagePath = selectedFile.getAbsolutePath();
            String resourcesPath = "src/main/resources/images/"; // Replace with the actual resources folder path
            Path source = Paths.get(profileImagePath);
            Path destination = Paths.get(resourcesPath + source.getFileName());
            if (!Files.exists(destination)){
                Files.copy(source, destination);
            }

            //set image and initialize checkboxes
            Image profileImage = new Image(selectedFile.toURI().toString());
            String imagePath = "/images/" + source.getFileName();
            currentUser.setImage(imagePath,-1);
            profileImageView.setImage(profileImage);
            check0.setSelected(false);
            check1.setSelected(false);
            check2.setSelected(false);
            check3.setSelected(false);
            check0.setVisible(true);
            check1.setVisible(true);
            check2.setVisible(true);
            check3.setVisible(true);
        }
    }

    public void checkBoxCheck(MouseEvent mouseEvent) {
        if (check0.isSelected()){
            currentUser.setImage(RandomGenerator.randomImagePath(0),0);
        }
        else if (check1.isSelected()){
            currentUser.setImage(RandomGenerator.randomImagePath(1),1);
        }
        else if (check2.isSelected()){
            currentUser.setImage(RandomGenerator.randomImagePath(2),2);

        }
        else if (check3.isSelected()){
            currentUser.setImage(RandomGenerator.randomImagePath(3),3);
        }
        check0.setSelected(false);
        check1.setSelected(false);
        check2.setSelected(false);
        check3.setSelected(false);

        switch (currentUser.getImageNumber()){
            case 0:
                check0.setVisible(false);
                check1.setVisible(true);
                check2.setVisible(true);
                check3.setVisible(true);
                break;
            case 1:
                check0.setVisible(true);
                check1.setVisible(false);
                check2.setVisible(true);
                check3.setVisible(true);
                break;
            case 2:
                check0.setVisible(true);
                check1.setVisible(true);
                check2.setVisible(false);
                check3.setVisible(true);
                break;
            case 3:
                check0.setVisible(true);
                check1.setVisible(true);
                check2.setVisible(true);
                check3.setVisible(false);
                break;
            default:
                check0.setVisible(true);
                check1.setVisible(true);
                check2.setVisible(true);
                check3.setVisible(true);
                break;
        }
        String path = String.valueOf(LoginMenu.class.getResource(currentUser.getImagePath()));
        Image avatar = new Image(path);
        profileImageView.setImage(avatar);
    }
}
