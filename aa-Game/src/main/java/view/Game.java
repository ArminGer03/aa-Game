package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Ball;
import view.Animations.BallAnimation;

import java.net.URL;


public class Game extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        URL url = LoginMenu.class.getResource("/fxml/gamePane.fxml");
        Pane gamePane = FXMLLoader.load(url);
        Scene scene = new Scene(gamePane,650,800);


        Ball ball = createBall(gamePane);

        //gamePane.getChildren().add(ball);

        ballShooting(gamePane,ball);

        stage.setScene(scene);
        stage.show();
    }

    private Ball createBall(Pane gamePane) {
        Ball ball = new Ball(325,600,10);

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

    private void ballShooting(Pane gamePane , Ball ball) {
        //gameController.setMeteorite(meteorite);
        gamePane.getChildren().add(ball);
        BallAnimation ballAnimation =
                new BallAnimation(ball,gamePane);
        ball.setBallAnimation(ballAnimation);
        ballAnimation.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
