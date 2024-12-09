package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Set;

public class GameViewController {

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
    private Canvas hangmanCanvas;

    private GraphicsContext gc;

    @FXML
    public void initialize() {
        createLetterButtons();
        gc = hangmanCanvas.getGraphicsContext2D();
    }

    private void createLetterButtons() {
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            Button letterButton = new Button(String.valueOf(letter));
            letterButton.setOnAction(e -> letterButton.setDisable(true));
            letterButtonsGrid.add(letterButton, (letter - 'A') % 7, (letter - 'A') / 7);
        }
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

        drawHangman(wrongGuesses.size());
    }

    private void drawHangman(int wrongGuesses) {
    //clear obszaru rysowania
        gc.clearRect(0, 0, hangmanCanvas.getWidth(), hangmanCanvas.getHeight());

        // Rysowanie podstawy szubienicy (stałe elementy)
        gc.strokeLine(20, 230, 180, 230); // Podstawa
        gc.strokeLine(50, 230, 50, 20);   // Słup
        gc.strokeLine(50, 20, 150, 20);   // Belka pozioma
        gc.strokeLine(150, 20, 150, 50);  // Lina

        // Rysowanie części ciała w zależności od liczby błędów
        if (wrongGuesses >= 1) {
            gc.strokeOval(130, 50, 40, 40); // Głowa
        }
        if (wrongGuesses >= 2) {
            gc.strokeLine(150, 90, 150, 150); // Tułów
        }
        if (wrongGuesses >= 3) {
            gc.strokeLine(150, 100, 120, 130); // Lewa ręka
        }
        if (wrongGuesses >= 4) {
            gc.strokeLine(150, 100, 180, 130); // Prawa ręka
        }
        if (wrongGuesses >= 5) {
            gc.strokeLine(150, 150, 120, 180); // Lewa noga
        }
        if (wrongGuesses >= 6) {
            gc.strokeLine(150, 150, 180, 180); // Prawa noga
        }
    }

    public Button getBackButton() {
        return backButton;
    }

    public void setHintButtonAction(Runnable action) {
        hintButton.setOnAction(e -> action.run());
    }
}
