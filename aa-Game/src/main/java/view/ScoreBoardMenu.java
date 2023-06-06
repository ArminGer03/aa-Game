package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;
import utility.DataClass;

import java.net.URL;
import java.util.ArrayList;

public class ScoreBoardMenu extends Application {
    @FXML
    private VBox rankMonitor;

    private String mode = "Medium";
    @Override
    public void start(Stage stage) throws Exception {
        URL url = LoginMenu.class.getResource("/fxml/ScoreBoardMenu.fxml");
        assert url != null;
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize(){
        displayUsers(DataClass.getUsers());
    }

    public void sortEasy(){
        removeAll();
        mode = "Easy";
        displayUsers(DataClass.getUsersByMode("Easy"));
    }


    public void sortMedium() {
        removeAll();
        mode = "Medium";
        displayUsers(DataClass.getUsersByMode("Medium"));
    }

    public void sortHard() {
        removeAll();
        mode = "Hard";
        displayUsers(DataClass.getUsersByMode("Hard"));
    }

    public void goToMainMenu() throws Exception {
        new MainMenu().start(DataClass.getStage());
    }

    private void removeAll() {
        while (rankMonitor.getChildren().size() != 0){
            rankMonitor.getChildren().remove(0);
        }
    }

    private void displayUsers(ArrayList<User> users){
        StackPane stackPane;
        for (int i= 0 ; i < 10 ; i++){
            if (i < users.size()){
                String show = users.get(i).getUsername().toString() + "    " + users.get(i).getHighScores().get(mode);
                stackPane = makeStackPane(i ,show );
            } else {
                stackPane = makeStackPane(i , "---");
            }
            rankMonitor.getChildren().add(stackPane);
        }

    }

    private StackPane makeStackPane(int i,String data){
        Text rank = new Text(String.valueOf(i+1));
        rank.setStyle("-fx-font: 20 system ; -fx-font-weight: bold");

        Text userData = new Text(data);
        userData.setStyle("-fx-font: 20 system ; -fx-font-weight: bold");

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(30);

        hBox.getChildren().add(rank);
        hBox.getChildren().add(userData);


        Rectangle rectangle = new Rectangle(400 , 35);

        switch (i) {
            case 0 -> rectangle.setFill(Color.GOLD);
            case 1 -> rectangle.setFill(Color.SILVER);
            case 2 -> rectangle.setFill(Color.BROWN);
            default -> rectangle.setFill(Color.NAVY);
        }

        StackPane stackPane = new StackPane(rectangle,hBox);
        stackPane.setAlignment(Pos.CENTER);
        return stackPane;
    }



}
