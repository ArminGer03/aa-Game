package contoller;

import model.Shooter;

public class GameController {

    private String username;

//    private Meteorite meteorite;
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

}
