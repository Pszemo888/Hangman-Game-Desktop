package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import model.HangmanModel;
import utils.FileLoader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileController {
    private final HangmanModel model;
    private final FileLoader fileLoader;

    public FileController(HangmanModel model, String defaultFileName) {
        this.model = model;
        this.fileLoader = new FileLoader(defaultFileName);
    }

    // Wczytanie słów z pliku podanego przez użytkownika
    public void loadWordsFromFile() {
        Optional<String> fileNameOpt = promptForTextInput("Enter the file name (e.g., words.txt):");
        if (fileNameOpt.isEmpty()) {
            showAlert("Operation canceled.");
            return; // Operacja anulowana
        }

        String fileName = fileNameOpt.get();
        try {
            List<String> words = fileLoader.loadWordsFromFile(fileName);
            model.updateWords(words);
            showAlert("Loaded " + words.size() + " words from " + fileName);
        } catch (IOException e) {
            showAlert("Error loading file. Please check the file name and try again.");
        }
    }

    // Wybór tematu na podstawie pliku
    public void setSubject(String subjectFileName) {
        try {
            List<String> subjectWords = fileLoader.loadWordsFromFile(subjectFileName);
            model.updateWords(subjectWords);
            showAlert("Loaded subject words from " + subjectFileName + ": " + subjectWords.size() + " words.");
        } catch (IOException e) {
            showAlert("Error loading subject. Please check the file and try again.");
        }
    }

    // Dodanie niestandardowych słów do pliku
    public void addCustomWords() {
        Optional<String> inputOpt = promptForTextInput("Enter words separated by commas:");
        if (inputOpt.isEmpty()) {
            showAlert("Operation canceled.");
            return;
        }

        String input = inputOpt.get();
        List<String> customWords = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        try {
            fileLoader.saveCustomWords(customWords, "WordSource/custom_words.txt");
            model.updateWords(customWords);
            showAlert("Added custom words: " + customWords);
        } catch (IOException e) {
            showAlert("Error saving custom words. Please try again.");
        }
    }

    // Tworzenie nowego pliku ze słowami podanymi przez użytkownika
    public void createNewWordFile() {
        Optional<String> fileNameOpt = promptForTextInput("Enter the name for the new file (e.g., new_words.txt):");
        if (fileNameOpt.isEmpty()) {
            showAlert("File creation canceled.");
            return;
        }

        String fileName = fileNameOpt.get();

        Optional<String> inputOpt = promptForTextInput("Enter words separated by commas:");
        if (inputOpt.isEmpty()) {
            showAlert("Operation canceled.");
            return;
        }

        String input = inputOpt.get();
        List<String> newWords = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        try {
            fileLoader.saveCustomWords(newWords, fileName);
            showAlert("Created file " + fileName + " with words: " + newWords);
        } catch (IOException e) {
            showAlert("Error creating file. Please try again.");
        }
    }

    // Pobieranie tekstu od użytkownika za pomocą TextInputDialog
    private Optional<String> promptForTextInput(String prompt) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Input Required");
        dialog.setHeaderText(null);
        dialog.setContentText(prompt);

        return dialog.showAndWait();
    }

    // Wyświetlenie komunikatu za pomocą Alert
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
