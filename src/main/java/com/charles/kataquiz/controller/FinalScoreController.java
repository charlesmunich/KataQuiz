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
    private QuizService quiz;

    @FXML
    private Label finalScoreLabel;

    public void init(QuizService quiz) {
        this.quiz = quiz;

        double quizPercent = ((double) this.quiz.getFinalScore() / this.quiz.getTotalNumberOfQuestions()) * 100;
        int score = this.quiz.getFinalScore();
        int numQuestions = this.quiz.getTotalNumberOfQuestions();

        finalScoreLabel.setText(
                String.format("Final Score: %d / %d (%.1f%%)",
                        score,
                        numQuestions,
                        quizPercent));
    }

    @FXML
    private void goHome(){
        QuizApp.setScene("home.fxml");
    }

    @FXML
    private void onExport() {
        FileChooser fc = createFileChooser();
        File file = fc.showSaveDialog(null);

        Quiz quiz = new Quiz(this.quiz.getQuestions()); //TODO dupe names

        if (file != null) {
            new QuizRepository().saveQuiz(quiz, file.toPath());
        }
    }

    private FileChooser createFileChooser() {
        FileChooser fs = new FileChooser();
        fs.setInitialDirectory(new File(System.getProperty("user.home")));
        fs.setTitle("Save Quiz");
        fs.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("KataQuiz", "*.json"));
        fs.setInitialFileName("open-trivia-database-quiz.json");
        return fs;
    }
}
