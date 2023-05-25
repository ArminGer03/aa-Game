package model;

import javafx.scene.shape.Circle;
import view.Animations.BallAnimation;

import java.awt.*;

public class Ball extends Circle {
    private BallAnimation ballAnimation;
    private int number;


    public Ball(double v, double v1, double v2, int number) {
        super(v, v1, v2);
        this.number = number;
    }

    public void setBallAnimation(BallAnimation ballAnimation) {
        this.ballAnimation = ballAnimation;
    }

}
