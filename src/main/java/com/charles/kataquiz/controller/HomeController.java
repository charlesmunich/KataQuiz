package com.charles.kataquiz.controller;

import com.charles.kataquiz.QuizApp;
import com.charles.kataquiz.service.ImportService;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

public class HomeController {
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

        File file = fs.showOpenDialog(null);

        ImportService.startQuiz(file.toPath());
    }

    private FileChooser chooseFile() {
        FileChooser fs = new FileChooser();
        fs.setInitialDirectory(new File(System.getProperty("user.home")));
        fs.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("KataQuiz", "*.json"));
        return fs;
    }
}
