module com.example.hangman {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    opens controller to javafx.fxml;  // Eksport pakietu controller dla javafx.fxml

    opens com.example.hangman to javafx.fxml;
    exports com.example.hangman;
    exports view;
    opens view to javafx.fxml;
}