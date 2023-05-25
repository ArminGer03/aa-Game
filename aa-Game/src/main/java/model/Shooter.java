package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Shooter extends Rectangle {
    public Shooter(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.setFill(new ImagePattern(
                new Image(Shooter.class.getResource("/images/pic1.jpeg").toExternalForm())));
    }
}
