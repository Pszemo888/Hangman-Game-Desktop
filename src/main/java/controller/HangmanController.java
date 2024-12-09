
package controller;

import javafx.application.Platform;
import javafx.stage.Stage;
import model.HangmanModel;
import view.*;
import view.settings.GameDurationView;
import view.settings.DifficultyView;
import java.util.Timer;
import java.util.TimerTask;
import view.settings.SubjectView;
import view.settings.WordSourceView;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class HangmanController {
    private final HangmanModel model;
    private final Stage stage;
    private Timer displayTimer;
    private final FileController fileController;
    public HangmanController(HangmanModel model, Stage stage) {
        this.model = model;
        this.stage = stage;
        this.fileController = new FileController(model, "WordSource/words.txt");
    }

    // Wyświetlenie menu głównego
    public void displayMainMenu() {
        MenuView menuView = new MenuView(stage);

        // Podłączanie akcji do przycisków
        menuView.getStartGameButton().setOnAction(e -> startNewGame());
        menuView.getSettingsButton().setOnAction(e -> displaySettingsMenu());
        menuView.getStatisticsButton().setOnAction(e -> displayStatistics());
        menuView.getAchievementsButton().setOnAction(e -> displayAchievements());
        menuView.getRulesButton().setOnAction(e -> displayRules());
        menuView.getExitButton().setOnAction(e -> stage.close());

        stage.setScene(menuView.createScene());
    }

    // Rozpoczęcie nowej gry
    public void startNewGame() {
        model.resetGame();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameView.fxml"));
            Parent root = loader.load();
            GameView gameView = loader.getController();

            // Ustawienie akcji dla liter od A do Z
            setupLetterButtonActions(gameView);
            setupHintButtonAction(gameView);

            gameView.getBackButton().setOnAction(e -> stopGameAndReturnToMenu());

            stage.setScene(new Scene(root, 1000, 600));
            updateGameState(gameView);
            startDisplayTimer(gameView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Timer
    private void setupLetterButtonActions(GameView gameView) {
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            char currentLetter = letter;
            gameView.setLetterButtonAction(currentLetter, () -> handleGuess(gameView, currentLetter));
        }
    }

    // Podłączanie akcji do przycisku podpowiedzi
    private void setupHintButtonAction(GameView gameView) {
        gameView.setHintButtonAction(() -> handleHint(gameView));
    }

    // Obsługa odgadywania litery
    private void handleGuess(GameView gameView, char letter) {
        boolean gameEnded = model.processGuess(letter);
        updateGameState(gameView);

        if (gameEnded) {
            endGame();
        }
    }

    // Obsługa użycia podpowiedzi
    private void handleHint(GameView gameView) {
        model.useHint();
        updateGameState(gameView);
    }

    // Aktualizacja stanu gry w widoku
    private void updateGameState(GameView gameView) {
        gameView.updateGameState(
                model.getWordDisplay(),
                model.getAttemptsLeft(),
                model.getScore(),
                model.getWrongGuesses(),
                model.getRemainingTime(),
                model.getHintCount(),
                model.getMaxHints()
        );
    }

    // Timer do odświeżania stanu gry
    private void startDisplayTimer(GameView gameView) {
        displayTimer = new Timer();
        displayTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    updateGameState(gameView);

                    if (model.isGameOver() || model.isTimeUp()) {
                        stopDisplayTimer();
                        endGame();
                    }
                });
            }
        }, 0, 1000);
    }

    private void stopDisplayTimer() {
        if (displayTimer != null) {
            displayTimer.cancel();
            displayTimer = null;
        }
    }

    // Powrót do menu głównego
    private void stopGameAndReturnToMenu() {
        stopDisplayTimer();
        displayMainMenu();
    }

    // Zakończenie gry
    private void endGame() {
        Platform.runLater(() -> {
            String message = model.isWordGuessed() ?
                    "You won! The word was: " + model.getWordToGuess() :
                    "You lost! The word was: " + model.getWordToGuess();

            stopGameAndReturnToMenu();
        });
    }


    // Wyświetlenie menu ustawień
    public void displaySettingsMenu() {
        SettingsView settingsView = new SettingsView();


        settingsView.getDifficultyButton().setOnAction(e -> displayDifficultySettings());
        settingsView.getGameDurationButton().setOnAction(e -> displayGameDurationSettings());
        settingsView.getWordSourceButton().setOnAction(e -> displayWordSourceSettings());
        settingsView.getSubjectButton().setOnAction(e -> displaySubjectSettings());
        settingsView.getBackButton().setOnAction(e -> displayMainMenu());

        stage.setScene(settingsView.createScene());
    }

    // Wyświetlenie opcji ustawień czasu gry
    private void displayGameDurationSettings() {
        GameDurationView durationView = new GameDurationView();


        durationView.getSet30SecButton().setOnAction(e -> {
            model.setGameDuration(30);
            System.out.println("Game duration set to 30 seconds");
        });

        durationView.getSet60SecButton().setOnAction(e -> {
            model.setGameDuration(60);
            System.out.println("Game duration set to 60 seconds");
        });

        durationView.getSet90SecButton().setOnAction(e -> {
            model.setGameDuration(90);
            System.out.println("Game duration set to 90 seconds");
        });

        durationView.getCustomDurationButton().setOnAction(e -> {
            try {
                int customDuration = Integer.parseInt(durationView.getCustomDurationField().getText());
                if (customDuration > 0) {
                    model.setGameDuration(customDuration);
                    System.out.println("Game duration set to " + customDuration + " seconds");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid custom duration");
            }
        });

        durationView.getBackButton().setOnAction(e -> displaySettingsMenu());

        stage.setScene(durationView.createScene());
    }
    private void displayWordSourceSettings() {
        WordSourceView wordSourceView = new WordSourceView();

        // Wczytanie słów z pliku
        wordSourceView.getLoadFileButton().setOnAction(e -> {
            fileController.loadWordsFromFile();
        });

        // Tworzenie nowego pliku z własnymi słowami
        wordSourceView.getCreateFileButton().setOnAction(e -> {
            fileController.createNewWordFile();
        });

        // Dodanie niestandardowych słów do istniejącego pliku
        wordSourceView.getAddWordsButton().setOnAction(e -> {
            fileController.addCustomWords();
        });

        // Powrót do menu ustawień
        wordSourceView.getBackButton().setOnAction(e -> displaySettingsMenu());

        stage.setScene(wordSourceView.createScene());
    }
    // Wyświetlenie statystyk
    private void displayStatistics() {
        StatisticsView statisticsView = new StatisticsView();

        // Pobranie statystyk z modelu
        int wins = model.getWins();
        int losses = model.getLosses();
        int score = model.getScore();

        statisticsView.updateStatistics(wins, losses, score);

        statisticsView.getBackButton().setOnAction(e -> displayMainMenu());


        stage.setScene(statisticsView.createScene());
    }

    // Wyświetlenie osiągnięć
    private void displayAchievements() {
        AchievementsView achievementsView = new AchievementsView();

        achievementsView.updateAchievements("First Win", "High Score");
        achievementsView.getBackButton().setOnAction(e -> displayMainMenu());

        stage.setScene(achievementsView.createScene());
    }

    // Wyświetlenie zasad gry
    private void displayRules() {
        RulesView rulesView = new RulesView();

        rulesView.getBackButton().setOnAction(e -> displayMainMenu());

        stage.setScene(rulesView.createScene());
    }

    // Opcje trudności
    private void displayDifficultySettings() {
        DifficultyView difficultyView = new DifficultyView();

        difficultyView.getEasyButton().setOnAction(e -> {
            model.setDifficulty(8);
            System.out.println("Difficulty set to Easy (8 attempts)");
        });

        difficultyView.getMediumButton().setOnAction(e -> {
            model.setDifficulty(6);
            System.out.println("Difficulty set to Medium (6 attempts)");
        });

        difficultyView.getHardButton().setOnAction(e -> {
            model.setDifficulty(4);
            System.out.println("Difficulty set to Hard (4 attempts)");
        });

        difficultyView.getExtremeButton().setOnAction(e -> {
            model.setDifficulty(1);
            System.out.println("Difficulty set to Extreme (1 attempt)");
        });


        difficultyView.getBackButton().setOnAction(e -> displaySettingsMenu());

        stage.setScene(difficultyView.createScene());
    }


    // Opcje tematyki
    private void displaySubjectSettings() {
        SubjectView subjectView = new SubjectView();

        subjectView.getTechnologyButton().setOnAction(e -> {
            fileController.setSubject("technology.txt");
            System.out.println("Subject set to Technology");
        });

        subjectView.getAnimalsButton().setOnAction(e -> {
            fileController.setSubject("animals.txt");
            System.out.println("Subject set to Animals");
        });

        subjectView.getCountriesButton().setOnAction(e -> {
            fileController.setSubject("countries.txt");
            System.out.println("Subject set to Countries");
        });

        subjectView.getBackButton().setOnAction(e -> displaySettingsMenu());

        stage.setScene(subjectView.createScene());
    }
}
