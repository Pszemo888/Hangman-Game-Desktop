package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AchievementsView {
    private final Button backButton;
    private final Label achievementsLabel;

    public AchievementsView() {
        backButton = new Button("Back");
        achievementsLabel = new Label("Achievements:");
    }

    public Scene createScene() {
        VBox layout = new VBox(10);
        layout.getChildren().addAll(achievementsLabel, backButton);
        return new Scene(layout, 400, 300);
    }

    public void updateAchievements(String... achievements) {
        achievementsLabel.setText("Achievements:\n" + String.join("\n", achievements));
    }

    public Button getBackButton() {
        return backButton;
    }
}
