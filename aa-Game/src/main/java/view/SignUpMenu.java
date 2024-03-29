package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class SignUpMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL url = SignUpMenu.class.getResource("/fxml/SignUpMenu.fxml");
        assert url != null;
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane,520,400);
        stage.setScene(scene);
        stage.show();
    }

}
