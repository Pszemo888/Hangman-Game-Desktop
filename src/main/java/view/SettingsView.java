package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SettingsView {
    private final VBox layout;

    private final Button difficultyButton;
    private final Button gameDurationButton;
    private final Button wordSourceButton;
    private final Button subjectButton;
    private final Button backButton;

    public SettingsView() {
        layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("settings-layout");

        Label title = new Label("Settings");
        title.getStyleClass().add("settings-title");


        difficultyButton = createStyledButton("Difficulty");
        gameDurationButton = createStyledButton("Game Duration");
        wordSourceButton = createStyledButton("Word Source");
        subjectButton = createStyledButton("Subject");

        backButton = createStyledButton("Back");

        layout.getChildren().addAll(title, difficultyButton, gameDurationButton, wordSourceButton, subjectButton, backButton);
    }

    public Scene createScene() {
        Scene scene = new Scene(layout, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        return scene;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("settings-button");
        return button;
    }

    public Button getDifficultyButton() {
        return difficultyButton;
    }

    public Button getGameDurationButton() {
        return gameDurationButton;
    }

    public Button getWordSourceButton() {
        return wordSourceButton;
    }

    public Button getSubjectButton() {
        return subjectButton;
    }

    public Button getBackButton() {
        return backButton;
    }
}
