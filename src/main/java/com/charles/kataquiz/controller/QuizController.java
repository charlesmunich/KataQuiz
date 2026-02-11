package com.charles.kataquiz.controller;

import com.charles.kataquiz.*;
import com.charles.kataquiz.model.Question;
import com.charles.kataquiz.service.HintService;
import com.charles.kataquiz.service.QuizService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.List;

public class QuizController {
    private HintService hintService;
    private QuizService quizService;

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

    private ToggleGroup answerGroup;

    @FXML
    private void initialize() {
        this.answerGroup = new ToggleGroup();

        this.radioButtonA.setToggleGroup(answerGroup);
        this.radioButtonB.setToggleGroup(answerGroup);
        this.radioButtonC.setToggleGroup(answerGroup);
        this.radioButtonD.setToggleGroup(answerGroup);
    }

    public void startQuiz(List<Question> questions){
        this.quizService = new QuizService(questions);
        loadQuestion();
    }

    private void loadQuestion() {
        this.answerGroup.selectToggle(null);

        Question question = this.quizService.getCurrentQuestion();

        questionLabel.setText(question.getQuestionText());

        List<String> answers = this.quizService.getShuffledAnswers(question);

        this.radioButtonA.setText(answers.get(0));
        this.radioButtonB.setText(answers.get(1));
        this.radioButtonC.setText(answers.get(2));
        this.radioButtonD.setText(answers.get(3));
    }

    @FXML
    public void submitAnswer(){
        if(this.answerGroup.getSelectedToggle() != null){
            RadioButton selected = (RadioButton) answerGroup.getSelectedToggle();

            String selectedAnswer = selected.getText();
            this.quizService.submitAnswer(selectedAnswer);

            if(this.quizService.isOver()){
                QuizApp.setScene("final-score.fxml");
            } else {
                this.quizService.nextQuestion();
                loadQuestion();
            }
        }
    }
}
