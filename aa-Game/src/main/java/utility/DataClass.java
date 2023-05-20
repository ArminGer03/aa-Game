package utility;

import javafx.stage.Stage;

public class DataClass {
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        DataClass.stage = stage;
    }
}
