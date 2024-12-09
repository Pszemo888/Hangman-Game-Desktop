package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.HangmanModel;

import java.util.Set;

public class GameView {
    private final HangmanModel model;
    private final Stage stage;

    private Label wordLabel;
    private Label attemptsLabel;
    private Label scoreLabel;
    private Label timerLabel;
    private Label hintsLabel;
    private Button backButton;
    private Button hintButton; // Dodajemy przycisk podpowiedzi

    public GameView(HangmanModel model, Stage stage) {
        this.model = model;
        this.stage = stage;
        this.backButton = new Button("Back to Menu");
        this.hintButton = new Button("Use Hint"); // Inicjalizacja przycisku podpowiedzi
    }

    public Scene createScene() {
        VBox layout = new VBox(10);

        wordLabel = new Label(model.getWordDisplay());
        attemptsLabel = new Label("Attempts left: " + model.getAttemptsLeft());
        scoreLabel = new Label("Score: " + model.getScore());
        timerLabel = new Label("Time left: " + model.getRemainingTime() + "s");
        hintsLabel = new Label("Hints used: " + model.getHintCount() + "/" + model.getMaxHints());

        GridPane letterButtons = createLetterButtons();

        layout.getChildren().addAll(wordLabel, attemptsLabel, scoreLabel, timerLabel, hintsLabel, hintButton, letterButtons, backButton);

        hintButton.setOnAction(e -> handleHint()); // Obsługa użycia podpowiedzi

        Scene scene = new Scene(layout, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        return scene;
    }

    private GridPane createLetterButtons() {
        GridPane grid = new GridPane();
        int col = 0, row = 0;

        for (char letter = 'A'; letter <= 'Z'; letter++) {
            final char currentLetter = letter;
            Button letterButton = new Button(String.valueOf(letter));
            letterButton.setOnAction(e -> handleGuess(letterButton, currentLetter));
            grid.add(letterButton, col, row);
            col++;
            if (col > 6) {
                col = 0;
                row++;
            }
        }
        return grid;
    }

    private void handleGuess(Button letterButton, char letter) {
        boolean gameEnded = model.processGuess(letter); // Użycie processGuess
        letterButton.setDisable(true);

        updateGameState(
                model.getWordDisplay(),
                model.getAttemptsLeft(),
                model.getScore(),
                model.getWrongGuesses(),
                model.getRemainingTime(),
                model.getHintCount(),
                model.getMaxHints()
        );

        if (gameEnded) {
            String message = model.isWordGuessed() ?
                    "Congratulations! You guessed the word: " + model.getWordToGuess() :
                    "Game Over! The word was: " + model.getWordToGuess();
            showEndGameDialog(message);
        }
    }

    private void handleHint() {
        Character hintLetter = model.useHint();
        if (hintLetter != null) {
            updateGameState(
                    model.getWordDisplay(),
                    model.getAttemptsLeft(),
                    model.getScore(),
                    model.getWrongGuesses(),
                    model.getRemainingTime(),
                    model.getHintCount(),
                    model.getMaxHints()
            );
        } else {
            System.out.println("No more hints available or not enough attempts left.");
        }
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

    public void showEndGameDialog(String message) {
        System.out.println(message); // Możesz zamienić to na rzeczywisty dialog np. Alert
        backButton.fire(); // Automatycznie powrót do menu
    }
}
