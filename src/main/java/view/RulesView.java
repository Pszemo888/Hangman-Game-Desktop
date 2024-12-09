package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class RulesView {
    private final Button backButton;
    private final Label titleLabel;
    private final ScrollPane scrollPane;

    public RulesView() {

        backButton = new Button("Back");
        backButton.getStyleClass().add("back-button");


        titleLabel = new Label("Game Rules");
        titleLabel.getStyleClass().add("title-label");


        String rulesContent = String.join("\n",
                "1. The goal is to guess the hidden word letter by letter.",
                "2. You have a limited number of attempts to guess the entire word.",
                "3. Each wrong guess costs you one attempt and adds to the hangman's drawing.",
                "4. Using a hint reveals one letter but deducts additional attempts and points.",
                "5. The game ends when you either guess the word or run out of attempts.",
                "",
                "--- Scoring ---",
                "1. Start with 100 points.",
                "2. A correct letter adds 2 points to your score.",
                "3. Guessing the entire word earns an additional 50 points.",
                "4. Each hint used deducts 10 points.",
                "5. A wrong letter reduces the attempts but doesn't affect points.",
                "6. Failing to guess the word deducts 25 points from your score.",
                "",
                "Good luck and have fun!"
        );
        Label rulesLabel = new Label(rulesContent);
        rulesLabel.getStyleClass().add("rules-content");
        rulesLabel.setWrapText(true);

        // Scrollable pane for rules
        scrollPane = new ScrollPane(rulesLabel);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));
        scrollPane.getStyleClass().add("scroll-pane");

        // Main layout
        VBox layout = new VBox(15, titleLabel, scrollPane, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getStyleClass().add("rules-layout");
    }

    public Scene createScene() {
        Scene scene = new Scene(new VBox(15, titleLabel, scrollPane, backButton), 600, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        return scene;
    }

    public Button getBackButton() {
        return backButton;
    }
}
