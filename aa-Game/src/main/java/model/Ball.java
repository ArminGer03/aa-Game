package model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.Animations.BallAnimation;

import java.awt.*;

public class Ball extends Pane {
    private Circle circle;
    private Text numberText;
    private BallAnimation ballAnimation;
    private int number;


    public Ball(double centerX, double centerY, double radius, int number) {
        circle = new Circle(centerX, centerY, radius);
        numberText = new Text(Integer.toString(number + 1));
        numberText.setFill(Color.WHITE);
        this.number = number;
        numberText.setX(centerX - numberText.getLayoutBounds().getWidth() / 2);
        numberText.setY(centerY + numberText.getLayoutBounds().getHeight() / 4);
        getChildren().addAll(circle, numberText);

        circle.centerXProperty().addListener((observable, oldValue, newValue) -> {
            numberText.setX(newValue.doubleValue() - numberText.getLayoutBounds().getWidth() / 2);
        });

        circle.centerYProperty().addListener((observable, oldValue, newValue) -> {
            numberText.setY(newValue.doubleValue() + numberText.getLayoutBounds().getHeight() / 4);
        });
    }

    public Circle getCircle() {
        return circle;
    }

    public void setBallAnimation(BallAnimation ballAnimation) {
        this.ballAnimation = ballAnimation;
    }

}
