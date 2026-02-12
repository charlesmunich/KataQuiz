package com.charles.kataquiz.controller;

import com.charles.kataquiz.QuizApp;
import com.charles.kataquiz.service.ImportService;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

public class HomeController {
    @FXML
    private VBox root;

    @FXML
    public void onTakeQuiz() {
        QuizApp.setScene("quiz-setup.fxml");
    }

    @FXML
    private void onCreateQuiz(){
        QuizApp.setScene("create-quiz.fxml");
    }

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

    private FileChooser chooseFile() {
        FileChooser fs = new FileChooser();

        fs.setInitialDirectory(new File(System.getProperty("user.home")));
        fs.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("JSON", "*.json"));

        return fs;
    }
}
