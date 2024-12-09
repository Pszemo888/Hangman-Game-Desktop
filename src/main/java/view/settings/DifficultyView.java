package view.settings;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        layout.getStyleClass().add("settings-layout");

        Label title = new Label("Select Difficulty Level");
        title.getStyleClass().add("settings-title");


        easyButton = createStyledButton("Easy");
        mediumButton = createStyledButton("Medium");
        hardButton = createStyledButton("Hard");
        extremeButton = createStyledButton("Extreme");
        backButton = createStyledButton("Back");


        layout.getChildren().addAll(title, easyButton, mediumButton, hardButton, extremeButton, backButton);
    }

    public Scene createScene() {
        Scene scene = new Scene(layout, 1000, 600); // Ustawienie rozmiaru okna
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm()); // Załadowanie pliku CSS
        return scene;
    }

    // Metoda pomocnicza do tworzenia przycisków ze stylem
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("settings-button");
        return button;
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
