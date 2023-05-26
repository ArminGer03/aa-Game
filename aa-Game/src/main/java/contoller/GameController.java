package contoller;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import model.Ball;
import model.MainCircle;
import model.Shooter;
import view.Animations.BallAnimation;
import view.Game;

public class GameController {

    private String username;

    private Circle borderCircle;

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
            BallAnimation ballAnimation =
                    new BallAnimation(balls[Game.shotBalls],gamePane);
            balls[Game.shotBalls].setBallAnimation(ballAnimation);
            ballAnimation.play();
            Game.shotBalls++;
        }
    }

    public Circle getBorderCircle() {
        return borderCircle;
    }
}
