package contoller;

import javafx.scene.layout.Pane;
import model.Ball;
import model.Shooter;
import view.Animations.BallAnimation;
import view.Game;

public class GameController {

    private String username;

    public GameController(String username) {
        this.username = username;
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

}
