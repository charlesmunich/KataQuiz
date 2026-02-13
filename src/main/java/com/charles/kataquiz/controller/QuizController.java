/*
 * Author: Charles Loeffler
 * Last Updated: 02/12/2026
 */

package com.charles.kataquiz.controller;

import com.charles.kataquiz.QuizApp;
import com.charles.kataquiz.model.Question;
import com.charles.kataquiz.service.QuizService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.List;

/**
 * Controller responsible for running a quiz.
 * Handles question display, navigation, and answer submission.
 */
public class QuizController {
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

    @FXML
    private Button nextButton;

    @FXML
    private Button backButton;

    @FXML
    private Label questionNumber;

    private ToggleGroup answerGroup;

    /**
     * Initializes radio buttons and listeners.
     */
    @FXML
    private void initialize() {
        this.answerGroup = new ToggleGroup();

        this.radioButtonA.setToggleGroup(answerGroup);
        this.radioButtonB.setToggleGroup(answerGroup);
        this.radioButtonC.setToggleGroup(answerGroup);
        this.radioButtonD.setToggleGroup(answerGroup);

        nextButton.setDisable(true);
        backButton.setDisable(true);

        answerGroup.selectedToggleProperty().addListener((oldObservable, oldToggle, newToggle) -> {
            nextButton.setDisable(newToggle == null);
        });

    }

    /**
     * Returns the user to the home screen.
     */
    @FXML
    private void goHome(){
        QuizApp.setScene("home.fxml");
    }

    /**
     * Saves the selected answer and moves to the next question
     * or final score screen.
     */
    @FXML
    public void nextQuestion(){
        if(this.answerGroup.getSelectedToggle() != null){
            RadioButton selected = (RadioButton) answerGroup.getSelectedToggle();

            String selectedAnswer = selected.getText();
            this.quizService.submitAnswer(selectedAnswer);

            if(this.quizService.isLastQuestion()){

                QuizApp.setScene("final-score.fxml", controller -> {
                    FinalScoreController finalScoreController = (FinalScoreController) controller;
                    finalScoreController.init(this.quizService);
                });

            } else {
                this.quizService.nextQuestion();
                loadQuestion();
            }
        } else {
            QuizApp.showInfoPopup("Please select an answer.");
        }
        updateBackButton();
    }

    /**
     * Moves to the previous question if possible.
     */
    @FXML
    public void previousQuestion(){
        if(!this.quizService.isFirstQuestion()){
            this.quizService.previousQuestion();
            loadQuestion();
        }
        updateBackButton();
    }

    /**
     * Starts a new quiz using the given questions.
     *
     * @param questions the list of quiz questions
     */
    public void startQuiz(List<Question> questions){
        this.quizService = new QuizService(questions);
        loadQuestion();
    }

    /**
     * Loads and displays the current question.
     */
    private void loadQuestion() {
        this.answerGroup.selectToggle(null);

        Question question = this.quizService.getCurrentQuestion();

        this.questionLabel.setText(question.getQuestionText());

        List<String> answers = this.quizService.getShuffledAnswers(question);

        this.radioButtonA.setText(answers.get(0));
        this.radioButtonB.setText(answers.get(1));
        this.radioButtonC.setText(answers.get(2));
        this.radioButtonD.setText(answers.get(3));

        String savedAnswer = this.quizService.getSavedAnswer();

        if (savedAnswer != null) {
            for (RadioButton rb : List.of(
                    this.radioButtonA,
                    this.radioButtonB,
                    this.radioButtonC,
                    this.radioButtonD
            )) {
                if (rb.getText().equals(savedAnswer)) {
                    answerGroup.selectToggle(rb);
                    break;
                }
            }
        }

        if(this.quizService.isLastQuestion()){
            this.nextButton.setText("Submit");
        } else {
            this.nextButton.setText("Next");
        }

        this.questionNumber.setText(
                "Question: "
                    + this.quizService.getCurrentQuestionNumber()
                    + " / "
                    + this.quizService.getTotalNumberOfQuestions());
    }

    /**
     * Starts a quiz that was imported from a file.
     *
     * @param quizService the quiz service containing loaded questions
     */
    public void startImportedQuiz(QuizService quizService) {
        this.quizService = quizService;
        loadQuestion();
    }

    /**
     * Updates the back button enabled state.
     */
    private void updateBackButton(){
        backButton.setDisable(this.quizService.isFirstQuestion());
    }
}
