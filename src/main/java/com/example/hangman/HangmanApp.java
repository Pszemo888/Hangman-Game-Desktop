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

        HangmanModel model = new HangmanModel(words);

        HangmanController menuController = new HangmanController(model, stage);

        menuController.displayMainMenu();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
