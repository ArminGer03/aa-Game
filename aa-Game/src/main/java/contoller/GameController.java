package contoller;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.Ball;
import model.MainCircle;
import model.Shooter;
import utility.DataClass;
import utility.RandomGenerator;
import view.Animations.RotateAnimation;
import view.Animations.ShootAnimation;
import view.Game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Math.*;

public class GameController {

    private String username;
    private static Circle borderCircle;
    private static ArrayList<Ball> rotatingBalls;
    private static MainCircle mainCircle;

    private static int phase;
    private static int  WindDegree;
    private static double iceProgress;
    public static boolean iceModeActivated;
    public static boolean phase2Activated;
    public static boolean phase3Activated;
    public static boolean phase4Activated;

    public static Timer timerIce;
    public static Timer phase2Timer;
    public static Timer phase3Timer;
    public static Timer phase4Timer;

    static {
        rotatingBalls = new ArrayList<>();
        iceProgress = 0;
        iceModeActivated = false;
        phase2Activated = false;
        phase3Activated = false;
        phase4Activated = false;
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

            if(ballsTouching()){
               rotateAnimation.stop();
               Game.finish();
            }

            return true;
        }
        return false;
    }

    public static boolean ballsTouching(){
        ArrayList<Ball> balls = getRotatingBalls();
        for (int i = 0; i < balls.size(); i++) {
            for (int j = i+1; j < balls.size(); j++) {
                if (balls.get(i).intersects(balls.get(j))) {
                    return true;
                }
            }
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
            if(ball.getVisibility()){
                Line line = new Line(ball.getCircle().getCenterX(), ball.getCircle().getCenterY(),
                        mainCircle.getCircleBorder().getCenterX(), mainCircle.getCircleBorder().getCenterY());
                Game.getGamePane().getChildren().add(line);
                line.toBack();
            }

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
        else if (percentShot >= 0.75 && percentShot <= 1){
            phase = 4;
        }
        Game.updatePhaseLabel(phase);
        return phase;
    }
    public static double calculateIceProgress(){
        double ice = getIceProgress();
        if (!iceModeActivated){
            ice += 0.2;
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
        RotateAnimation rotateAnimation = Game.getRotateAnimation();
        double slowerSpeed = rotateAnimation.getRotationSpeed() / 3;
        Timer timer = new Timer ();
        timerIce = timer;
        timer.schedule ( new TimerTask() {
            @Override
            public void run() {

                Platform.runLater ( ()->{
                    if(Game.isFinished){
                        timer.cancel();
                    }
                    iceModeActivated = true;
                    iceProgress -= 0.25;
                    rotateAnimation.setRotationSpeed(slowerSpeed);
                    if (iceProgress <= 0) {
                        iceModeActivated = false;
                        iceProgress = 0.0;
                        rotateAnimation.setRotationSpeed(slowerSpeed * 3);
                        timer.cancel();
                    }
                    Game.updateIceProgressBar(iceProgress);
                } );

            }
        }, 0, (long) (1000) );

    }

    public void activatePhase2(){
        if (!phase2Activated){
            phase2Activated = true;
            changeDirectionPhase2();
            changeBallSizePhase2();
        }
    }

    public void changeDirectionPhase2(){
        RotateAnimation rotateAnimation = Game.getRotateAnimation();
        Timer timer = new Timer ();
        phase2Timer = timer;
        timer.schedule ( new TimerTask() {
            @Override
            public void run() {

                Platform.runLater ( ()->{
                    if(Game.isFinished){
                        timer.cancel();
                    }
                    rotateAnimation.setDirection(-1 * rotateAnimation.getDirection());
                } );

            }
        }, 0, (long) (4000) );

    }
    public void changeBallSizePhase2(){
        new Thread(() -> {
            while (true) {
                for (Ball ball : rotatingBalls) {
                    ball.getCircle().setRadius(11);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Ball ball : rotatingBalls) {
                    ball.getCircle().setRadius(9);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void activatePhase3(){
        if (!phase3Activated){
            phase3Activated = true;
            Timer timer = new Timer ();
            phase3Timer = timer;
            AtomicBoolean visibility = new AtomicBoolean(false);
            timer.schedule ( new TimerTask() {
                @Override
                public void run() {
                    if(Game.isFinished){
                        visibility.set(true);
                        for (Ball ball : rotatingBalls) {
                            ball.setVisible(visibility.get());
                            ball.setVisibility(visibility.get());
                        }
                        timer.cancel();
                    }
                    else {
                        Platform.runLater ( ()->{
                            for (Ball ball : rotatingBalls) {
                                ball.setVisible(visibility.get());
                                ball.setVisibility(visibility.get());
                            }
                            visibility.set(!visibility.get());
                        } );
                    }

                }
            }, 0, (long) (1000) );

        }
    }

    public void activatePhase4(){
        if (!phase4Activated){
            phase4Activated = true;
            Timer timer = new Timer ();
            phase4Timer = timer;
            timer.schedule ( new TimerTask() {
                @Override
                public void run() {

                    Platform.runLater ( ()->{
                        if(Game.isFinished){
                            timer.cancel();
                        }
                        setWindDegree(RandomGenerator.randomWindAngle());
                    } );

                }
            }, 0, (long) (5000) );

        }
    }


    public static int getWindDegree() {
        return WindDegree;
    }

    public static void setWindDegree(int windDegree) {
        GameController.WindDegree = windDegree;
        Game.updateWindTracker(windDegree);
    }
}
