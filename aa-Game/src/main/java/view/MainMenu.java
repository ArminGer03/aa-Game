package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utility.DataClass;

import java.net.URL;

public class MainMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        DataClass.setStage(stage);
        URL url = LoginMenu.class.getResource("/fxml/MainMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane,650,400);
        stage.setScene(scene);
        stage.show();
    }

}
