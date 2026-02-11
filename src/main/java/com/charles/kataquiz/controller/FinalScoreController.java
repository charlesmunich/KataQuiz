package com.charles.kataquiz.controller;

import com.charles.kataquiz.QuizApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FinalScoreController {
    @FXML
    private Label finalScore;

    @FXML
    private void goHome(){
        QuizApp.setScene("home.fxml");
    }
}
