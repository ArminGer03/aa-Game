package view.Animations;

import contoller.GameController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Ball;
import view.Game;


public class ShootAnimation extends Transition {

    private Ball ball;
    private Pane pane;
    private double speed = 10;
    private static double WindAngle = 0;
    public ShootAnimation(Ball ball, Pane pane){

        this.ball = ball;
        this.pane = pane;

        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(5000));

    }


    public static void setWindAngle(double windAngle) {
        WindAngle = windAngle;
    }

    @Override
    protected void interpolate(double v) {
        double y = ball.getCircle().getCenterY();
        double x = ball.getCircle().getCenterX();


        if (y <= 20) {
            pane.getChildren().remove(ball);
            this.stop();
            Game.finish();
        }
        if (x < 0 || x > 500) {
            pane.getChildren().remove(ball);
            this.stop();
            Game.finish();
        }
        else if(GameController.collideWithMainBorder(ball)){
            this.stop();
            if (Game.shotBalls == Game.BALLS_COUNT){
                Game.finish();
            }
        }
        else{
            y = ball.getCircle().getCenterY() - speed * Math.cos(WindAngle);
            x = ball.getCircle().getCenterX() - speed * Math.sin(WindAngle);
            ball.getCircle().setCenterY(y);
            ball.getCircle().setCenterX(x);
        }


    }
}
