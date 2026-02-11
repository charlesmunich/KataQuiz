package com.charles.kataquiz.controller;

import com.charles.kataquiz.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.List;

public class QuizController {
    private QuizService quizService;
    private HintService hintService;
    private int currentQuestionIndex;
    private int maxQuestionIndex;
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

    ToggleGroup answerGroup;

    private List<Question> questions;

    @FXML
    private void initialize() {
        this.answerGroup = new ToggleGroup();

        this.radioButtonA.setToggleGroup(answerGroup);
        this.radioButtonB.setToggleGroup(answerGroup);
        this.radioButtonC.setToggleGroup(answerGroup);
        this.radioButtonD.setToggleGroup(answerGroup);
    }

    public void startQuiz(List<Question> questions){
        this.questions = questions;
        this.maxQuestionIndex = questions.size() - 1;
        this.currentQuestionIndex = 0;
        this.score = 0;

        loadQuestion();
    }

    private void loadQuestion() {
        this.answerGroup.selectToggle(null);

        Question question = questions.get(currentQuestionIndex);

        questionLabel.setText(question.getQuestionText());

        List<String> answers = AnswerUtil.combineAndShuffle(question);

        this.radioButtonA.setText(answers.get(0));
        this.radioButtonB.setText(answers.get(1));
        this.radioButtonC.setText(answers.get(2));
        this.radioButtonD.setText(answers.get(3));
    }

    public void startUserQuiz(UserQuiz quiz){

    }

    @FXML
    public void submitAnswer(){
        if(this.answerGroup.getSelectedToggle() != null){
            RadioButton selected = (RadioButton) answerGroup.getSelectedToggle();

            String selectedAnswer = selected.getText();
            Question current = questions.get(currentQuestionIndex);

            if(selectedAnswer.equals(current.getQuestionText())){
                score++;
            }

            if(this.currentQuestionIndex >= maxQuestionIndex){
                QuizApp.setScene("final-score.fxml");
            } else {
                nextQuestion();
            }
        }
    }

    public void useFiftyFifty(){

    }

    public void nextQuestion(){
        this.currentQuestionIndex++;
        loadQuestion();
    }

    public void endQuiz(){

    }
}
