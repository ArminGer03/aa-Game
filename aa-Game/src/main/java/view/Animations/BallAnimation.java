package view.Animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Ball;

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
        double y = ball.getCircle().getCenterY() - 5;

        if (y <= 20) {
            pane.getChildren().remove(ball);
            this.stop();
        }
        ball.getCircle().setCenterY(y);
    }
}
