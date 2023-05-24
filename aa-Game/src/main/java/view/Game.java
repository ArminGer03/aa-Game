package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Ball;

import java.net.URL;


public class Game extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        URL url = LoginMenu.class.getResource("/fxml/gamePane.fxml");
        Pane gamePane = FXMLLoader.load(url);
        Scene scene = new Scene(gamePane,650,800);


        Ball ball = createBall(gamePane);

        gamePane.getChildren().add(ball);

        stage.setScene(scene);
        stage.show();
    }

    private Ball createBall(Pane gamePane) {
        Ball ball = new Ball(325,700,10);

//        ball.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                String keyName = keyEvent.getCode().getName();
//
////                if (keyName.equals("Space"))
////                    Game.gameController.shoot(rocket, gamePane);
//            }
//        });

        return ball;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
