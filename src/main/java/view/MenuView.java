package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuView {
    private final Stage stage;
    private final Button startGameButton;
    private final Button settingsButton;
    private final Button statisticsButton;
    private final Button achievementsButton;
    private final Button rulesButton;
    private final Button exitButton;

    public MenuView(Stage stage) {
        this.stage = stage;

        startGameButton = new Button("Start Game");
        settingsButton = new Button("Settings");
        statisticsButton = new Button("Statistics");
        achievementsButton = new Button("Achievements");
        rulesButton = new Button("Rules");
        exitButton = new Button("Exit");

        startGameButton.getStyleClass().add("menu-button");
        settingsButton.getStyleClass().add("menu-button");
        statisticsButton.getStyleClass().add("menu-button");
        achievementsButton.getStyleClass().add("menu-button");
        rulesButton.getStyleClass().add("menu-button");
        exitButton.getStyleClass().add("menu-button");
    }

    public Scene createScene() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("menu-layout");


        Label title = new Label("Hangman");
        title.getStyleClass().add("menu-title");

        layout.getChildren().addAll(title, startGameButton, settingsButton, statisticsButton, achievementsButton, rulesButton, exitButton);

        Scene scene = new Scene(layout, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        return scene;
    }


    public Button getStartGameButton() {
        return startGameButton;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }

    public Button getStatisticsButton() {
        return statisticsButton;
    }

    public Button getAchievementsButton() {
        return achievementsButton;
    }

    public Button getRulesButton() {
        return rulesButton;
    }

    public Button getExitButton() {
        return exitButton;
    }
}
