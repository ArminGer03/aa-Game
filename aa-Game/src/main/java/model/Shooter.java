package model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Shooter extends Rectangle {
    public Shooter(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.setFill(Color.TRANSPARENT);
    }
}
