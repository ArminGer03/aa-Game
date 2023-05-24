module aa.Game {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;

    exports view;
    opens view to javafx.fxml;
    exports view.Controllers;
    opens view.Controllers to javafx.fxml;
    opens model to com.google.gson;
}