/*
package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameView {
    private final Stage stage;

    private Label wordLabel;
    private Label attemptsLabel;
    private Label scoreLabel;
    private Label timerLabel;
    private Label hintsLabel;
    private Button backButton;
    private Button hintButton;

    private List<Button> letterButtons;

    public GameView(Stage stage) {
        this.stage = stage;
        this.backButton = new Button("Back to Menu");
        this.hintButton = new Button("Use Hint");
        this.letterButtons = new ArrayList<>();


        createLetterButtons();
    }

    public Scene createScene() {
        VBox layout = new VBox(10);

        wordLabel = new Label();
        attemptsLabel = new Label();
        scoreLabel = new Label();
        timerLabel = new Label();
        hintsLabel = new Label();

        GridPane letterButtonsGrid = new GridPane();
        int col = 0, row = 0;
        for (Button letterButton : letterButtons) {
            letterButtonsGrid.add(letterButton, col, row);
            col++;
            if (col > 6) {
                col = 0;
                row++;
            }
        }

        layout.getChildren().addAll(wordLabel, attemptsLabel, scoreLabel, timerLabel, hintsLabel, hintButton, letterButtonsGrid, backButton);

        Scene scene = new Scene(layout, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        return scene;
    }

    private void createLetterButtons() {
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            Button letterButton = new Button(String.valueOf(letter));
            letterButtons.add(letterButton);
        }
    }


    public List<Button> getLetterButtons() {
        return letterButtons;
    }

    public void setLetterButtonAction(char letter, Runnable action) {
        for (Button button : letterButtons) {
            if (button.getText().equals(String.valueOf(letter))) {
                button.setOnAction(e -> {
                    button.setDisable(true);
                    action.run();
                });
            }
        }
    }

    public void setHintButtonAction(Runnable action) {
        hintButton.setOnAction(e -> action.run());
    }

    public void updateGameState(String wordDisplay, int attemptsLeft, int score,
                                Set<Character> wrongGuesses, int remainingTime,
                                int hintCount, int maxHints) {
        wordLabel.setText(wordDisplay);
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        scoreLabel.setText("Score: " + score);
        timerLabel.setText("Time left: " + remainingTime + "s");
        hintsLabel.setText("Hints used: " + hintCount + "/" + maxHints);
    }

    public Button getBackButton() {
        return backButton;
    }
}
*/
package view;

import java.util.Set;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GameView {

    @FXML
    private Label wordLabel;

    @FXML
    private Label attemptsLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label timerLabel;

    @FXML
    private Label hintsLabel;

    @FXML
    private Button hintButton;

    @FXML
    private Button backButton;

    @FXML
    private GridPane letterButtonsGrid;

    @FXML
    public void initialize() {
        createLetterButtons();
    }

    private void createLetterButtons() {
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            Button letterButton = new Button(String.valueOf(letter));
            letterButton.setOnAction(e -> letterButton.setDisable(true));
            letterButtonsGrid.add(letterButton, (letter - 'A') % 7, (letter - 'A') / 7);
        }
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getHintButton() {
        return hintButton;
    }

    public void setLetterButtonAction(char letter, Runnable action) {
        for (javafx.scene.Node node : letterButtonsGrid.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                if (button.getText().equals(String.valueOf(letter))) {
                    button.setOnAction(e -> {
                        button.setDisable(true);
                        action.run();
                    });
                }
            }
        }
    }

    public void updateGameState(String wordDisplay, int attemptsLeft, int score,
                                Set<Character> wrongGuesses, int remainingTime, int hintCount, int maxHints) {
        wordLabel.setText(wordDisplay);
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        scoreLabel.setText("Score: " + score);
        timerLabel.setText("Time left: " + remainingTime + "s");
        hintsLabel.setText("Hints used: " + hintCount + "/" + maxHints);

       // System.out.println("Wrong guesses: " + wrongGuesses);
    }

    public void setHintButtonAction(Runnable action) {
        hintButton.setOnAction(e -> action.run());
    }
}
