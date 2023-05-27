package view;

import contoller.GameController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Ball;
import model.MainCircle;
import model.Shooter;
import utility.DataClass;
import utility.RandomGenerator;
import view.Animations.RotateAnimation;

import java.net.URL;


public class Game extends Application {

    public static GameController gameController;
    public static int BALLS_COUNT = DataClass.getCurrentUser().getBalls();
    public static int shotBalls = 0;

    private static RotateAnimation rotateAnimation;

    private static Pane gamePane;


    @Override
    public void start(Stage stage) throws Exception {
        //make scene
        URL url = LoginMenu.class.getResource("/fxml/gamePane.fxml");
        Pane gamePane = FXMLLoader.load(url);
        Scene scene = new Scene(gamePane,500,800);
        //main circle initializing
        Color mainCircleColor = DataClass.getCurrentUser().getColor();
        MainCircle mainCircle = new MainCircle(250 , 350, mainCircleColor);
        //gameController
        gameController = new GameController(mainCircle.getCircleBorder());
        gameController.setMainCircle(mainCircle);


        //create objects
        Ball[] balls = createBalls(BALLS_COUNT);
        Shooter shooter = createShooter(gamePane,balls);
        Bounds bounds = shooter.getBoundsInParent();
        setBallsPositionInShooter(bounds,balls);

        //todo phase bar
        //todo ice bar
        //todo timer


        //add objects to gamePane
        gamePane.getChildren().add(shooter);
        gamePane.getChildren().addAll(balls);
        gamePane.getChildren().add(mainCircle);
        randomInitialBalls(gamePane);

        setGamePane(gamePane);


        gamePane.getChildren().get(0).requestFocus();
        stage.setScene(scene);
        stage.show();
    }

    private Shooter createShooter(Pane gamePane,Ball[] balls) {
        Shooter shooter = new Shooter(245,590,10,185);
        shooter.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();

                //todo phase checker
                if (keyName.equals("Left")){
                    Game.gameController.moveLeft(shooter);
                }
                else if (keyName.equals("Right")){
                    Game.gameController.moveRight(shooter);
                }
                else if (keyName.equals("Space")){
                    Game.gameController.ballShooting(gamePane , balls);
                }
                //todo add tab
                //todo add esc for pause
                
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

    public static void randomInitialBalls(Pane gamePane){
        RotateAnimation rotateAnimation = new RotateAnimation(GameController.getRotatingBalls(),
                GameController.getBorderCircle(), 1,0.01,true);

        for (int i = 0; i<6; i++){
            double angle = RandomGenerator.randomAngle();
            Ball ball = new Ball(0,0,10,0);
            ball.getNumberText().setVisible(false);
            gamePane.getChildren().add(ball);
            ball.setAngleWithCenter(angle);
            gameController.getRotatingBalls().add(ball);
            setRotateAnimation(rotateAnimation);
            rotateAnimation.play();
        }
    }

    public static RotateAnimation getRotateAnimation() {
        return rotateAnimation;
    }

    public static void setRotateAnimation(RotateAnimation rotateAnimation) {
        Game.rotateAnimation = rotateAnimation;
    }

    public static Pane getGamePane() {
        return gamePane;
    }

    public void setGamePane(Pane gamePane) {
        this.gamePane = gamePane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
