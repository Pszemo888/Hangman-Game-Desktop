package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StatisticsView {
    private final Button backButton;
    private final Label statisticsLabel;
    private final Scene scene;

    public StatisticsView() {
        // Tworzenie przycisku powrotu z globalną klasą CSS dla przycisków
        backButton = new Button("Back");
        backButton.getStyleClass().add("back-button");

        // Tworzenie etykiety statystyk z globalną klasą CSS dla tytułów
        statisticsLabel = new Label("Statistics:");
        statisticsLabel.getStyleClass().add("title-label");

        // Układ główny
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("settings-layout");
        layout.getChildren().addAll(statisticsLabel, backButton);

        // Tworzenie sceny i ładowanie arkusza stylów CSS
        scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    public Scene createScene() {
        return scene;
    }

    public void updateStatistics(int wins, int losses, int score) {
        statisticsLabel.setText("Wins: " + wins + "\nLosses: " + losses + "\nScore: " + score);
    }

    public Button getBackButton() {
        return backButton;
    }
}
