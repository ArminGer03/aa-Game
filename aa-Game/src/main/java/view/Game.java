package view;

import contoller.GameController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
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

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;


public class Game extends Application {

    public static GameController gameController;
    public static int BALLS_COUNT = 10;
    public static int shotBalls = 0;


    @Override
    public void start(Stage stage) throws Exception {
        //todo new gameController
        gameController = new GameController("salam");

        URL url = LoginMenu.class.getResource("/fxml/gamePane.fxml");
        Pane gamePane = FXMLLoader.load(url);
        Scene scene = new Scene(gamePane,500,800);

        Ball[] balls = createBalls(BALLS_COUNT);
        Shooter shooter = createShooter(gamePane,balls);
        Bounds bounds = shooter.getBoundsInParent();
        setBallsPositionInShooter(bounds,balls);



        gamePane.getChildren().addAll(shooter);
        gamePane.getChildren().addAll(balls);

        gamePane.getChildren().get(0).requestFocus();
        stage.setScene(scene);
        stage.show();
    }

    private Shooter createShooter(Pane gamePane,Ball[] balls) {
        Shooter shooter = new Shooter(250,590,10,185);
        shooter.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();

                if (keyName.equals("Left")){
                    Game.gameController.moveLeft(shooter);
                }
                else if (keyName.equals("Right")){
                    Game.gameController.moveRight(shooter);
                }
                else if (keyName.equals("Space")){
                    Game.gameController.ballShooting(gamePane , balls);
                }

                Bounds bounds = shooter.getBoundsInParent();
                setBallsPositionInShooter(bounds,balls);

            }
        });

        return shooter;
    }

    private Ball[] createBalls(int n){
        Ball balls[] = new Ball[n];

        for (int i = 0; i<n ; i++){
            balls[i] = new Ball(100,100,10,i);
        }

        return balls;
    }

    private void setBallsPositionInShooter( Bounds bounds, Ball[] balls){

        for (int i = shotBalls; i < BALLS_COUNT ; i++){
            balls[i].getCircle().setCenterX(bounds.getMinX() + bounds.getWidth() / 2);
            if (shotBalls < 5){
                if (i > 5 + shotBalls)
                    balls[i].getCircle().setCenterY(bounds.getMaxY());
                else
                    balls[i].getCircle().setCenterY(bounds.getMinY() + (i - shotBalls) * bounds.getHeight() / 5);
            }
            else{
                balls[i].getCircle().setCenterY(bounds.getMinY() + (i - shotBalls) * bounds.getHeight() / 5);
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
