package com.charles.kataquiz.controller;

import com.charles.kataquiz.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.util.List;

public class QuizController {
    private QuizService quizService;
    private HintService hintService;
    private int currentQuestionIndex;
    private int score;
    private boolean hintUsed;

    @FXML
    private Label questionLabel;

    @FXML
    private RadioButton radioButtonA;

    @FXML
    private RadioButton radioButtonB;

    @FXML
    private RadioButton radioButtonC;

    @FXML
    private RadioButton radioButtonD;

    public void startQuiz(List<Question> questions){
        for (Question question : questions) {
            questionLabel.setText(question.getQuestionText());

            List<String> answers = AnswerUtil.combineAndShuffle(question);

            this.radioButtonA.setText(answers.get(0));
            this.radioButtonB.setText(answers.get(1));
            this.radioButtonC.setText(answers.get(2));
            this.radioButtonD.setText(answers.get(3));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void startUserQuiz(UserQuiz quiz){

    }

    @FXML
    public void submitAnswer(){

    }

    public void useFiftyFifty(){

    }

    public void nextQuestion(){

    }

    public void endQuiz(){

    }
}
