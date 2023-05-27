package view.Animations;

import contoller.GameController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Ball;


public class ShootAnimation extends Transition {

    private Ball ball;
    private Pane pane;

    public ShootAnimation(Ball ball, Pane pane){

        this.ball = ball;
        this.pane = pane;

        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(5000));

    }



    @Override
    protected void interpolate(double v) {
        double y = ball.getCircle().getCenterY();
        double x = ball.getCircle().getCenterX();

        //todo x axis check
        if (y <= 20) {
            pane.getChildren().remove(ball);
            this.stop();
        }
        else if(GameController.collideWithMainBorder(ball)){
            this.stop();
        }
        else{
            y = ball.getCircle().getCenterY() - 10;
            ball.getCircle().setCenterY(y);
            ball.getCircle().setCenterX(x);
        }



    }
}
