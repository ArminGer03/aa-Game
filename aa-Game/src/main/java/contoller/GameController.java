package contoller;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.Ball;
import model.MainCircle;
import model.Shooter;
import view.Animations.RotateAnimation;
import view.Animations.ShootAnimation;
import view.Game;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class GameController {

    private String username;
    private static Circle borderCircle;
    private static ArrayList<Ball> rotatingBalls;
    private static MainCircle mainCircle;

    static {
        //todo add loader
        rotatingBalls = new ArrayList<>();
    }

    public GameController(Circle borderCircle) {
        this.borderCircle = borderCircle;
    }
    public void moveLeft(Shooter shooter) {
        if (shooter.getX() > 60)
            shooter.setX(shooter.getX() - 15);
    }

    public void moveRight(Shooter shooter) {
        if (shooter.getX() < 420)
            shooter.setX(shooter.getX() + 15);
    }

    public void ballShooting(Pane gamePane , Ball[] balls) {
        if(Game.shotBalls < 10){
            ShootAnimation shootAnimation =
                    new ShootAnimation(balls[Game.shotBalls],gamePane);
            balls[Game.shotBalls].setBallAnimation(shootAnimation);
            shootAnimation.play();
            Game.shotBalls++;
        }
    }
    public static Boolean collideWithMainBorder(Ball ball){
        double y = ball.getCircle().getCenterY();
        double x = ball.getCircle().getCenterX();
        double distanceFromCenter = sqrt( pow( (y - borderCircle.getCenterY()) , 2) + pow( (x - borderCircle.getCenterX()) , 2) );
        if (distanceFromCenter <= borderCircle.getRadius()){

            double angle;
            if ( borderCircle.getCenterX() - x > 0){
                angle = Math.asin((y - borderCircle.getCenterY()) / distanceFromCenter) + PI/2;
            }
            else if (borderCircle.getCenterX() - x < 0){
                angle = 3 * PI / 2 - Math.asin((y- borderCircle.getCenterY()) / distanceFromCenter);
            }
            else {
                angle = PI;
            }
            ball.setAngleWithCenter(angle);
            rotatingBalls.add(ball);

            mainCircle.getNumberText().setText(Integer.toString(Game.shotBalls));

            RotateAnimation rotateAnimation = Game.getRotateAnimation();

            rotateAnimation.play();

            return true;
        }
        return false;
    }

    public static ArrayList<Ball> getRotatingBalls() {
        return rotatingBalls;
    }

    public static Circle getBorderCircle() {
        return borderCircle;
    }

    public void setMainCircle(MainCircle mainCircle) {
        this.mainCircle = mainCircle;
    }

    public static void drawLine(ArrayList<Ball> rotatingBalls){
        Game.getGamePane().getChildren().removeIf(node -> node instanceof Line);

        for ( Ball ball : rotatingBalls){
            Line line = new Line(ball.getCircle().getCenterX(), ball.getCircle().getCenterY(),
                    mainCircle.getCircleBorder().getCenterX(), mainCircle.getCircleBorder().getCenterY());
            Game.getGamePane().getChildren().add(line);
        }

    }

}
