package view.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.User;
import utility.DataClass;
import utility.DataLoader;
import utility.RandomGenerator;
import utility.Regexes;
import view.LoginMenu;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class ProfileMenuGController {
    User currentUser = DataClass.getCurrentUser();
    @FXML
    private ImageView profileImageView;
    @FXML
    private Label userLabel;
    @FXML
    private Label errorUser;
    @FXML
    private Label errorPass;
    @FXML
    private TextField newUsername;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmPassword;
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
        newUsername.textProperty().addListener((observable, oldText, newText)->{
            if (newText.length() < 6){
                errorUser.setText("Username must be bigger than 5 characters!");
            }
            else if (Regexes.getMatcher(newText, Regexes.USER_FORMAT) != null){
                errorUser.setText("Username must contain only letters and numbers!");
            }
            else if (DataClass.getUserByUsername(newText) != null){
                errorUser.setText("Username already taken!");
            }
            else
                errorUser.setText(null);
        });
        //password label message:
        newPassword.textProperty().addListener((observable, oldText, newText)->{
            if (newText.length() < 6){
                errorPass.setText("Password must be bigger than 5 characters!");
            }
            else if (Regexes.getMatcher(newText, Regexes.PASS_UPPER) == null){
                errorPass.setText("Password must contain at least one uppercase letter!");
            }
            else if (Regexes.getMatcher(newText, Regexes.PASS_LOWER) == null){
                errorPass.setText("Password must contain at least one lowercase letter!");
            }
            else if (Regexes.getMatcher(newText, Regexes.PASS_DIGIT) == null){
                errorPass.setText("Password must contain at least one number!");
            }
            else if (Regexes.getMatcher(newText, Regexes.PASS_NO_SPACE) == null){
                errorPass.setText("Password cant have spaces!");
            }
            else
                errorPass.setText(null);
        });
        //confirm password label message:
        confirmPassword.textProperty().addListener((observable, oldText, newText)->{
            if (confirmPassword.getText().isEmpty()){
                errorPass.setText("Confirm password is empty!");
            }
            else if (!confirmPassword.getText().equals(newPassword.getText())){
                errorPass.setText("Confirm password doesn't match with password!");
            }
            else
                errorPass.setText(null);
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

    public void change(MouseEvent mouseEvent) {
        if (!newUsername.getText().isEmpty() && errorUser.getText() == null){
            currentUser.setUsername(newUsername.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful!");
            alert.setHeaderText("change user");
            alert.setContentText("Your username was changed successfully!");
            alert.showAndWait();
        }
        if (!newPassword.getText().isEmpty() && !confirmPassword.getText().isEmpty() && errorPass.getText() == null){
            currentUser.setPassword(newPassword.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful!");
            alert.setHeaderText("change pass");
            alert.setContentText("Your password was changed successfully!");
            alert.showAndWait();
        }
        if (errorUser.getText() != null || errorPass.getText() != null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("failed");
            alert.setContentText("One of the fields are incorrect!");
            alert.showAndWait();
        }
        newUsername.setText("");
        newPassword.setText("");
        confirmPassword.setText("");
        errorUser.setText(null);
        errorPass.setText(null);
    }

    public void deleteUser(MouseEvent mouseEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to delete this user?");
        alert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            DataClass.getUsers().remove(currentUser);
            DataLoader.saveUsers();
            currentUser = null;
            DataClass.setCurrentUser(null);
            new LoginMenu().start(DataClass.getStage());
        } else {
           System.out.println("Cancelled!");
        }
    }
}
