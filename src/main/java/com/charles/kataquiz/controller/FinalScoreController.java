package com.charles.kataquiz.controller;

import com.charles.kataquiz.QuizApp;
import com.charles.kataquiz.service.QuizService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
}
