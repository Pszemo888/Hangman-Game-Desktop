package view.settings;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WordSourceView {
    private final Button loadFileButton;
    private final Button createFileButton;
    private final Button addWordsButton;
    private final Button backButton;
    private final VBox layout;

    public WordSourceView() {
        layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("settings-layout");

        Label title = new Label("Word Source Options");
        title.getStyleClass().add("settings-title");

        loadFileButton = createStyledButton("Load Words from File");
        createFileButton = createStyledButton("Create New Word File");
        addWordsButton = createStyledButton("Add Custom Words");
        backButton = createStyledButton("Back");

        layout.getChildren().addAll(title, loadFileButton, createFileButton, addWordsButton, backButton);
    }

    public Scene createScene() {
        Scene scene = new Scene(layout, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        return scene;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("settings-button");
        return button;
    }

    public Button getLoadFileButton() {
        return loadFileButton;
    }

    public Button getCreateFileButton() {
        return createFileButton;
    }

    public Button getAddWordsButton() {
        return addWordsButton;
    }

    public Button getBackButton() {
        return backButton;
    }
}
