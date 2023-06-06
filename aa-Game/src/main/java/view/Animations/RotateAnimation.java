package view.Animations;

import contoller.GameController;
import javafx.animation.Transition;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.Ball;

import java.util.ArrayList;

import static java.lang.Math.PI;

public class RotateAnimation extends Transition {
    private ArrayList<Ball> rotatingBalls;
    private Circle borderCircle;
    private int direction;
    private static double RotationSpeed;
    private boolean visibility;

    public RotateAnimation(ArrayList<Ball> rotatingBalls, Circle borderCircle, int direction, double rotationSpeed,
                           boolean visibility) {
        this.rotatingBalls = rotatingBalls;
        this.borderCircle = borderCircle;
        this.direction = direction;
        RotationSpeed = rotationSpeed;
        this.visibility = visibility;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(5000));
    }


    @Override
    protected void interpolate(double v) {

        for ( Ball ball:this.rotatingBalls ){
            double angle = ball.getAngleWithCenter();
            angle = (angle + RotationSpeed*direction) % (2 * PI);
            ball.setAngleWithCenter( angle );
            double x = borderCircle.getCenterX() - borderCircle.getRadius() * Math.sin(angle);
            double y = borderCircle.getCenterY() - borderCircle.getRadius() * Math.cos(angle);
            ball.getCircle().setCenterX(x);
            ball.getCircle().setCenterY(y);

            GameController.drawLine(rotatingBalls);
        }

    }

    public double getRotationSpeed() {
        return RotationSpeed;
    }

    public static void setRotationSpeed(double rotationSpeed) {
        RotationSpeed = rotationSpeed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
