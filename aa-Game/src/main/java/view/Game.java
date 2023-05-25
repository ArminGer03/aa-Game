package view;

import contoller.GameController;
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
import model.Shooter;
import view.Animations.BallAnimation;

import java.net.URL;
import java.util.Enumeration;


public class Game extends Application {

    public static GameController gameController;
    public static int BALLS = 10;
    public static int shotBalls = 0;


    @Override
    public void start(Stage stage) throws Exception {
        //todo new gameController
        gameController = new GameController("salam");

        URL url = LoginMenu.class.getResource("/fxml/gamePane.fxml");
        Pane gamePane = FXMLLoader.load(url);
        Scene scene = new Scene(gamePane,500,800);

        Shooter shooter = createShooter(gamePane);
        gamePane.getChildren().add(shooter);

        gamePane.getChildren().get(0).requestFocus();
        stage.setScene(scene);
        stage.show();
    }

    private Shooter createShooter(Pane gamePane) {
        Shooter shooter = new Shooter(250,600,10,200);
        shooter.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();

                if (keyName.equals("Left"))
                    Game.gameController.moveLeft(shooter);
                else if (keyName.equals("Right"))
                    Game.gameController.moveRight(shooter);
//                else if (keyName.equals("Space"))
//                    Game.gameController.shoot(shooter, gamePane);
            }
        });

        return shooter;
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
