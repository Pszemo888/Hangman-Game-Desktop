package com.example.hangman;

import controller.HangmanController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.HangmanModel;
import utils.FileLoader;

import java.util.List;

public class HangmanApp extends Application {
    @Override
    public void start(Stage stage) {

        stage.setTitle("Hangman Game");

        String filePath = "words.txt";

        FileLoader fileLoader = new FileLoader(filePath);
        List<String> words = fileLoader.loadWords();


        if (words.isEmpty()) {
            words = List.of("JAVA", "PROGRAMMING", "DEVELOPER", "SOFTWARE", "DEBUG", "COMPILE");
            System.out.println("Using default word list due to error loading file.");
        }

        HangmanModel model = new HangmanModel(words);

        HangmanController menuController = new HangmanController(model, stage);

        menuController.displayMainMenu();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
