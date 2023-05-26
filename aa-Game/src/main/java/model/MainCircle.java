package model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class MainCircle extends Pane {
    private Circle circle;
    private Circle circleBorder;
    private Text numberText;

    //todo add color
    public MainCircle(double centerX, double centerY ) {
        circle = new Circle(centerX, centerY, 100);
        circleBorder = new Circle(centerX, centerY, 185);
        circleBorder.setFill(Color.TRANSPARENT);
        numberText = new Text(Integer.toString(0));
        numberText.setFill(Color.WHITE);
        numberText.setX(centerX - numberText.getLayoutBounds().getWidth() / 2);
        numberText.setY(centerY + numberText.getLayoutBounds().getHeight() / 4);
        getChildren().addAll(circle, numberText , circleBorder);
    }

    public Circle getCircleBorder() {
        return circleBorder;
    }
}
