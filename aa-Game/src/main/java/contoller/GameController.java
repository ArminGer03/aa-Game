package contoller;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.Ball;
import model.MainCircle;
import model.Shooter;
import utility.DataClass;
import view.Animations.RotateAnimation;
import view.Animations.ShootAnimation;
import view.Game;

import java.util.ArrayList;

import static java.lang.Math.*;

public class GameController {

    private String username;
    private static Circle borderCircle;
    private static ArrayList<Ball> rotatingBalls;
    private static MainCircle mainCircle;

    private static int phase;
    private static double iceProgress;
    private static boolean iceModeActivated;

    static {
        //todo add loader
        rotatingBalls = new ArrayList<>();
        iceProgress = 0;
        iceModeActivated = false;
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
        if(Game.shotBalls < DataClass.getCurrentUser().getBalls()){
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

            //calculate phase
            phase = calculatePhase();

            iceProgress = calculateIceProgress();

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
            line.toBack();
        }

    }

    public static int calculatePhase(){
        int phase = 1;
        double percentShot = (double) Game.shotBalls / (double) Game.BALLS_COUNT;
        if (percentShot >= 0 && percentShot < 0.25){
            phase = 1;
        }
        if (percentShot >= 0.25 && percentShot < 0.5){
            phase = 2;
        }
        else if (percentShot >= 0.5 && percentShot < 0.75){
            phase = 3;
        }
        else if (percentShot >= 0.75 && percentShot < 1){
            phase = 4;
        }
        Game.updatePhaseLabel(phase);
        return phase;
    }
    public static double calculateIceProgress(){
        double ice = getIceProgress();
        if (!iceModeActivated){
            ice += 0.15;
            if(ice > 1){
                ice = 1;
            }
            Game.updateIceProgressBar(ice);
        }
        return ice;
    }


    public static int getPhase() {
        return phase;
    }

    public static double getIceProgress() {
        return iceProgress;
    }

    public void iceMode() {
        new Thread(() -> {
            RotateAnimation rotateAnimation = Game.getRotateAnimation();
            double slowerSpeed = rotateAnimation.getRotationSpeed() / 3;
            while (iceProgress > 0) {
                iceModeActivated = true;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                iceProgress -= 0.25;
                rotateAnimation.setRotationSpeed(slowerSpeed);
                if (iceProgress <= 0) {
                    iceModeActivated = false;
                    iceProgress = 0.0;
                    rotateAnimation.setRotationSpeed(slowerSpeed * 3);
                }
                Game.updateIceProgressBar(iceProgress);
            }
        }).start();
    }
}
