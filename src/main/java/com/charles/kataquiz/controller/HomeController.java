/*
 * Author: Charles Loeffler
 * Last Updated: 02/12/2026
 */

package com.charles.kataquiz.controller;

import com.charles.kataquiz.QuizApp;
import com.charles.kataquiz.service.ImportService;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Controller for the home screen.
 * Handles navigation to quiz setup, quiz creation,
 * and quiz import.
 */
public class HomeController {

    @FXML
    private VBox root;

    /**
     * Navigates to the quiz setup screen.
     */
    @FXML
    public void onTakeQuiz() {
        QuizApp.setScene("quiz-setup.fxml");
    }

    /**
     * Navigates to the quiz creation screen.
     */
    @FXML
    private void onCreateQuiz(){
        QuizApp.setScene("create-quiz.fxml");
    }

    /**
     * Opens a file chooser and imports a quiz if selected.
     */
    @FXML
    private void onImport(){
        FileChooser fs = chooseFile();
        fs.setInitialDirectory(new File(System.getProperty("user.home")));

        File file = fs.showOpenDialog(this.root.getScene().getWindow());

        if(file != null) {
            ImportService.startQuiz(file.toPath());
        } else {
            QuizApp.showInfoPopup("No file chosen, import aborted.");
        }
    }

    /**
     * Creates and configures a file chooser for JSON files.
     *
     * @return a configured FileChooser
     */
    private FileChooser chooseFile() {
        FileChooser fs = new FileChooser();

        fs.setInitialDirectory(new File(System.getProperty("user.home")));
        fs.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("JSON", "*.json"));

        return fs;
    }
}
