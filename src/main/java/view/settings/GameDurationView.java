package view.settings;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameDurationView {
    private final VBox layout;
    private final Button set30SecButton;
    private final Button set60SecButton;
    private final Button set90SecButton;
    private final Button backButton;

    // Custom Duration Elements
    private final TextField customDurationField;
    private final Button customDurationButton;

    public GameDurationView() {
        layout = new VBox(20); // Spacing between elements
        layout.setAlignment(Pos.CENTER); // Center alignment
        layout.getStyleClass().add("settings-layout"); // CSS class for the main layout

        Label title = new Label("Set Game Duration");
        title.getStyleClass().add("settings-title"); // CSS class for the title

        // Predefined duration buttons
        set30SecButton = createStyledButton("30 sec");
        set60SecButton = createStyledButton("60 sec");
        set90SecButton = createStyledButton("90 sec");

        // Custom Duration Input
        customDurationField = new TextField();
        customDurationField.setPromptText("Enter seconds");
        customDurationField.setPrefWidth(22);
        customDurationField.setPromptText("Custom duration");
        customDurationField.getStyleClass().add("custom-duration-field");

        customDurationButton = createStyledButton("Set");
        customDurationButton.setPrefWidth(80);
        customDurationButton.setPrefHeight(45);// Smaller width for the custom button

        backButton = createStyledButton("Back");

        // HBox for aligning TextField, Custom Button, and Back Button horizontally
        HBox customDurationBox = new HBox(10);
        customDurationBox.setAlignment(Pos.CENTER);
        customDurationBox.getChildren().addAll(customDurationField, customDurationButton);
        HBox durationButtonsBox = new HBox(20); // 10px spacing between buttons
        durationButtonsBox.setAlignment(Pos.CENTER); // Center alignment
        durationButtonsBox.getChildren().addAll(set30SecButton, set60SecButton, set90SecButton);
        // Add all elements to the layout
        layout.getChildren().addAll(title, durationButtonsBox, customDurationBox, backButton);
    }

    public Scene createScene() {
        Scene scene = new Scene(layout, 1000, 600); // Set the window size
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm()); // Load the CSS file
        return scene;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("settings-button"); // Add CSS class for buttons
        return button;
    }

    public Button getSet30SecButton() {
        return set30SecButton;
    }

    public Button getSet60SecButton() {
        return set60SecButton;
    }

    public Button getSet90SecButton() {
        return set90SecButton;
    }

    public TextField getCustomDurationField() {
        return customDurationField;
    }

    public Button getCustomDurationButton() {
        return customDurationButton;
    }

    public Button getBackButton() {
        return backButton;
    }
}
