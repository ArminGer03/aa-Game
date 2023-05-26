package view.Animations;

import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.Ball;
import view.Game;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class BallAnimation extends Transition {

    private Ball ball;
    private Pane pane;

    public BallAnimation(Ball ball, Pane pane){

        this.ball = ball;
        this.pane = pane;

        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(5000));

    }



    @Override
    protected void interpolate(double v) {

        Circle borderCircle = Game.gameController.getBorderCircle();
        double y = ball.getCircle().getCenterY();
        double x = ball.getCircle().getCenterX();
        if (y <= 20) {
            pane.getChildren().remove(ball);
            this.stop();
        }
        else if( pow( (y - borderCircle.getCenterY()) , 2) + pow( (x - borderCircle.getCenterX()) , 2) <= pow( borderCircle.getRadius() , 2)){

        }
        else
            y = ball.getCircle().getCenterY() - 10;


        ball.getCircle().setCenterY(y);
        ball.getCircle().setCenterX(x);



        //todo x axis check


    }
}
