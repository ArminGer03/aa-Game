package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utility.DataClass;

import java.net.URL;

public class LoginMenu extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        DataClass.setStage(stage);
        URL url = LoginMenu.class.getResource("/fxml/LoginMenu.fxml");
        assert url != null;
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane,520,400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}