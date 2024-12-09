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


    private final TextField customDurationField;
    private final Button customDurationButton;

    public GameDurationView() {
        layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("settings-layout");

        Label title = new Label("Set Game Duration");
        title.getStyleClass().add("settings-title");

        set30SecButton = createStyledButton("30 sec");
        set60SecButton = createStyledButton("60 sec");
        set90SecButton = createStyledButton("90 sec");

        customDurationField = new TextField();
        customDurationField.setPromptText("Enter seconds");
        customDurationField.setPrefWidth(200);
        customDurationField.setPromptText("Custom duration");
        customDurationField.getStyleClass().add("custom-duration-field");

        customDurationButton = createStyledButton("Set");
        customDurationButton.setPrefWidth(100);
        customDurationButton.setPrefHeight(50);

        backButton = createStyledButton("Back");


        HBox customDurationBox = new HBox(10);
        customDurationBox.setAlignment(Pos.CENTER);
        customDurationBox.getChildren().addAll(customDurationField, customDurationButton);
        HBox durationButtonsBox = new HBox(20);
        durationButtonsBox.setAlignment(Pos.CENTER);
        durationButtonsBox.getChildren().addAll(set30SecButton, set60SecButton, set90SecButton);
        layout.getChildren().addAll(title, durationButtonsBox, customDurationBox, backButton);
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
