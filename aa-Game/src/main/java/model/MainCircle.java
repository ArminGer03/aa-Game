package model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utility.DataClass;

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
        numberText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        numberText.setX(centerX - numberText.getLayoutBounds().getWidth() / 2 - 3);
        numberText.setY(centerY + numberText.getLayoutBounds().getHeight() / 2 - 3);
        getChildren().addAll(circle, numberText , circleBorder);
        numberText.toFront();

    }

    public Circle getCircleBorder() {
        return circleBorder;
    }

    public Text getNumberText() {
        return numberText;
    }
}
