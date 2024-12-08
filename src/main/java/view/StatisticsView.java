package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StatisticsView {
    private final Button backButton;
    private final Label statisticsLabel;

    public StatisticsView() {
        backButton = new Button("Back");
        statisticsLabel = new Label("Statistics:");
    }

    public Scene createScene() {
        VBox layout = new VBox(10);
        layout.getChildren().addAll(statisticsLabel, backButton);
        return new Scene(layout, 400, 300);
    }

    public void updateStatistics(int wins, int losses, int score) {
        statisticsLabel.setText("Wins: " + wins + "\nLosses: " + losses + "\nScore: " + score);
    }

    public Button getBackButton() {
        return backButton;
    }
}
