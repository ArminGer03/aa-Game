package model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import view.Animations.ShootAnimation;

public class Ball extends Pane {
    private Circle circle;
    private Text numberText;
    private ShootAnimation shootAnimation;
    private int number;
    private double angleWithCenter; //angle is calculated from y axis counter clockwise
    private boolean visibility ;


    public Ball(double centerX, double centerY, double radius, int number) {
        circle = new Circle(centerX, centerY, radius);
        numberText = new Text(Integer.toString(number + 1));
        numberText.setFill(Color.WHITE);
        this.number = number;
        visibility = true;
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

    public boolean intersects(Ball other) {
        double dx = this.getCircle().getCenterX() - other.getCircle().getCenterX();
        double dy = this.getCircle().getCenterY() - other.getCircle().getCenterY();
        double distance = Math.sqrt(dx*dx + dy*dy);
        return distance < this.getCircle().getRadius() + other.getCircle().getRadius();
    }

    public Circle getCircle() {
        return circle;
    }

    public void setBallAnimation(ShootAnimation shootAnimation) {
        this.shootAnimation = shootAnimation;
    }


    public double getAngleWithCenter() {
        return angleWithCenter;
    }

    public void setAngleWithCenter(double angleWithCenter) {
        this.angleWithCenter = angleWithCenter;
    }

    public Text getNumberText() {
        return numberText;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
