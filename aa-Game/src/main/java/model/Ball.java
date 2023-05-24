package model;

import javafx.scene.shape.Circle;
import view.Animations.BallAnimation;

import java.awt.*;

public class Ball extends Circle {
    private BallAnimation ballAnimation;


    public Ball(double v, double v1, double v2) {
        super(v, v1, v2);
    }

    public void setBallAnimation(BallAnimation ballAnimation) {
        this.ballAnimation = ballAnimation;
    }

}
