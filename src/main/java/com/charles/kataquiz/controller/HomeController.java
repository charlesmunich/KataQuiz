package com.charles.kataquiz.controller;

import com.charles.kataquiz.QuizApp;
import javafx.fxml.FXML;

public class HomeController {
    @FXML
    public void onTakeQuiz() {
        QuizApp.setScene("quiz-setup.fxml");
    }
}
