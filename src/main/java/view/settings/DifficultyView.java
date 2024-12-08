package view.settings;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class DifficultyView {
    private final VBox layout;
    private final Button easyButton;
    private final Button mediumButton;
    private final Button hardButton;
    private final Button extremeButton;
    private final Button backButton;

    public DifficultyView() {
        layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        easyButton = new Button("Easy");
        mediumButton = new Button("Medium");
        hardButton = new Button("Hard");
        extremeButton = new Button("Extreme");
        backButton = new Button("Back");

        layout.getChildren().addAll(easyButton, mediumButton, hardButton, extremeButton, backButton);
    }

    public Scene createScene() {
        return new Scene(layout, 1000, 600);
    }

    public Button getEasyButton() {
        return easyButton;
    }

    public Button getMediumButton() {
        return mediumButton;
    }

    public Button getHardButton() {
        return hardButton;
    }

    public Button getExtremeButton() {
        return extremeButton;
    }

    public Button getBackButton() {
        return backButton;
    }
}
