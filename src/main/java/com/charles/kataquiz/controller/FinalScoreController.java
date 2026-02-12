package com.charles.kataquiz.controller;

import com.charles.kataquiz.QuizApp;
import com.charles.kataquiz.model.Quiz;
import com.charles.kataquiz.repository.QuizRepository;
import com.charles.kataquiz.service.QuizService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;

public class FinalScoreController {

    private static final int PERCENT_MULTIPLIER = 100;

    private QuizService quizService;

    @FXML
    private Label finalScoreLabel;

    @FXML
    private void goHome(){
        QuizApp.setScene("home.fxml");
    }

    @FXML
    private void onExport() {
        FileChooser fc = createFileChooser();
        File file = fc.showSaveDialog(this.finalScoreLabel.getScene().getWindow()); //TODO root

        Quiz quiz = new Quiz(this.quizService.getQuestions());

        if (file != null) {
            new QuizRepository().saveQuiz(quiz, file.toPath());
        } else {
            QuizApp.showInfoPopup("No path chosen, export aborted.");
        }
    }

    public void init(QuizService quiz) {
        this.quizService = quiz;

        int finalScore = this.quizService.getFinalScore();
        int totalNumberOfQuestions = this.quizService.getTotalNumberOfQuestions();
        double quizPercent = ((double) finalScore / totalNumberOfQuestions) * PERCENT_MULTIPLIER;

        this.finalScoreLabel.setText(
                String.format("Final Score: %d / %d (%.1f%%)",
                        finalScore,
                        totalNumberOfQuestions,
                        quizPercent));
    }

    private FileChooser createFileChooser() {
        FileChooser fs = new FileChooser();

        fs.setInitialDirectory(new File(System.getProperty("user.home")));
        fs.setTitle("Save Quiz");
        fs.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("JSON", "*.json"));
        fs.setInitialFileName("open-trivia-database-quiz.json");

        return fs;
    }
}
