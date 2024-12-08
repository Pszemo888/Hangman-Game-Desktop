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
        menuView.getAchievementsButton().setOnAction(e -> displayAchievements());
        menuView.getRulesButton().setOnAction(e -> displayRules());
        menuView.getExitButton().setOnAction(e -> stage.close());

        stage.setScene(menuView.createScene());
    }

    // Rozpoczęcie nowej gry
    public void startNewGame() {
        model.resetGame();
        GameView gameView = new GameView(model, stage);

        // Obsługa powrotu do menu
        gameView.getBackButton().setOnAction(e -> stopGameAndReturnToMenu());

        stage.setScene(gameView.createScene());
        startDisplayTimer(gameView);
    }

    private void startDisplayTimer(GameView gameView) {
        displayTimer = new Timer();
        displayTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    // Aktualizacja widoku gry
                    gameView.updateGameState(
                            model.getWordDisplay(),
                            model.getAttemptsLeft(),
                            model.getScore(),
                            model.getWrongGuesses(),
                            model.getRemainingTime(),
                            model.getHintCount(),
                            model.getMaxHints()
                    );

                    // Sprawdzanie końca gry
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

    private void stopGameAndReturnToMenu() {
        stopDisplayTimer();
        displayMainMenu();
    }

    private void endGame() {
        Platform.runLater(() -> {
            String message = model.isWordGuessed() ?
                    "You won! The word was: " + model.getWordToGuess() :
                    "You lost! The word was: " + model.getWordToGuess();

            stopGameAndReturnToMenu();
        });
    }

    // Wyświetlenie ustawień
    public void displaySettingsMenu() {
        SettingsView settingsView = new SettingsView();


        settingsView.getDifficultyButton().setOnAction(e -> displayDifficultySettings());
        settingsView.getGameDurationButton().setOnAction(e -> displayGameDurationSettings());
       // settingsView.getWordSourceButton().setOnAction(e -> displayWordSourceSettings());
        settingsView.getSubjectButton().setOnAction(e -> displaySubjectSettings());
      //  settingsView.getThemeButton().setOnAction(e -> displayThemeSettings());
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

        // Opcja "Custom"
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

        // Powrót do menu ustawień
        durationView.getBackButton().setOnAction(e -> displaySettingsMenu());

        stage.setScene(durationView.createScene());
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

        // Obsługa wyboru poziomu trudności
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
    // Opcje motywu

}
