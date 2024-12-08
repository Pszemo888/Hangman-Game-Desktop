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

    public GameView(HangmanModel model, Stage stage) {
        this.model = model;
        this.stage = stage;
        this.backButton = new Button("Back to Menu"); // Inicjalizacja przycisku w konstruktorze
    }

    public Scene createScene() {
        VBox layout = new VBox(10);

        wordLabel = new Label(model.getWordDisplay());
        attemptsLabel = new Label("Attempts left: " + model.getAttemptsLeft());
        scoreLabel = new Label("Score: " + model.getScore());
        timerLabel = new Label("Time left: " + model.getRemainingTime() + "s");
        hintsLabel = new Label("Hints used: " + model.getHintCount() + "/" + model.getMaxHints());

        GridPane letterButtons = createLetterButtons();

        layout.getChildren().addAll(wordLabel, attemptsLabel, scoreLabel, timerLabel, hintsLabel, letterButtons, backButton);

        Scene scene = new Scene(layout, 1000, 600); // Tworzenie sceny
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm()); // Załadowanie pliku CSS
        return scene;
    }

    private GridPane createLetterButtons() {
        GridPane grid = new GridPane();
        int col = 0, row = 0;

        for (char letter = 'A'; letter <= 'Z'; letter++) {
            final char currentLetter = letter; // Tworzymy kopię finalną
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
        boolean correct = model.guessLetter(letter);
        letterButton.setDisable(true); // Wyłącz przycisk po kliknięciu
        updateGameState(
                model.getWordDisplay(),
                model.getAttemptsLeft(),
                model.getScore(),
                model.getWrongGuesses(),
                model.getRemainingTime(),
                model.getHintCount(),
                model.getMaxHints()
        );

        if (model.isGameOver()) {
            String message = model.isWordGuessed() ?
                    "Congratulations! You guessed the word: " + model.getWordToGuess() :
                    "Game Over! The word was: " + model.getWordToGuess();
            showEndGameDialog(message);
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
