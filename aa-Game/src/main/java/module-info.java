module aa.Game {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports view;
    opens view to javafx.fxml;
    exports view.Contollers;
    opens view.Contollers to javafx.fxml;
}