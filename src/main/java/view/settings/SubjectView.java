package view.settings;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SubjectView {
    private final VBox layout;
    private final Button technologyButton;
    private final Button animalsButton;
    private final Button countriesButton;
    private final Button backButton;

    public SubjectView() {
        layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("settings-layout");

        Label title = new Label("Select Subject");
        title.getStyleClass().add("settings-title");

        technologyButton = createStyledButton("Technology");
        animalsButton = createStyledButton("Animals");
        countriesButton = createStyledButton("Countries");
        backButton = createStyledButton("Back");

        layout.getChildren().addAll(title, technologyButton, animalsButton, countriesButton, backButton);
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

    public Button getTechnologyButton() {
        return technologyButton;
    }

    public Button getAnimalsButton() {
        return animalsButton;
    }

    public Button getCountriesButton() {
        return countriesButton;
    }

    public Button getBackButton() {
        return backButton;
    }
}
