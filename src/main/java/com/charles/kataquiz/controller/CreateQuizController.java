/*
 * Author: Charles Loeffler
 * Last Updated: 02/12/2026
 */

package com.charles.kataquiz.controller;

import com.charles.kataquiz.QuizApp;
import com.charles.kataquiz.model.Question;
import com.charles.kataquiz.model.Quiz;
import com.charles.kataquiz.repository.QuizRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Controller responsible for creating a custom quiz.
 * Allows adding, editing, removing, and exporting questions.
 */
public class CreateQuizController {

    private Quiz quiz;
    private int currentIndex;

    @FXML
    private VBox root;

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField questionField;

    @FXML
    private TextField answerField;

    @FXML
    private TextField falseField1;

    @FXML
    private TextField falseField2;

    @FXML
    private TextField falseField3;

    @FXML
    private Button removeQuestionButton;

    @FXML
    private Button previousButton;

    @FXML
    private Button nextButton;

    @FXML
    private Label questionCounter;

    /**
     * Initializes the quiz and loads the first question.
     */
    @FXML
    public void initialize() {
        this.quiz = new Quiz();
        this.currentIndex = 0;

        loadQuestion();
        updateUI();
    }

    /**
     * Returns the user to the home screen.
     */
    @FXML
    private void goHome(){
        QuizApp.setScene("home.fxml");
    }

    /**
     * Saves the current question's input fields to the model.
     */
    private void saveCurrentQuestion() {
        Question question = quiz.getQuestions().get(this.currentIndex);

        question.setQuestionText(this.questionField.getText());
        question.setCorrectAnswer(this.answerField.getText());
        question.setIncorrectAnswer(0, falseField1.getText());
        question.setIncorrectAnswer(1, falseField2.getText());
        question.setIncorrectAnswer(2, falseField3.getText());
    }

    /**
     * Adds a new question to the quiz.
     */
    @FXML
    private void addQuestion() {
        saveCurrentQuestion();

        this.quiz.addQuestion();
        this.currentIndex = this.quiz.getTotalQuestions() - 1;

        loadQuestion();
        updateUI();
    }

    /**
     * Removes the current question if more than one exists.
     */
    @FXML
    private void removeQuestion() {
        if (this.quiz.getTotalQuestions() > 1) {
            this.quiz.removeQuestion(this.currentIndex);

            if (this.currentIndex >= this.quiz.getTotalQuestions()) {
                this.currentIndex = this.quiz.getTotalQuestions() - 1;
            }

            loadQuestion();
            updateUI();
        }
    }

    /**
     * Moves to the next question.
     */
    @FXML
    private void nextQuestion() {
        saveCurrentQuestion();

        if (this.currentIndex < this.quiz.getTotalQuestions() - 1) {
            this.currentIndex++;
            loadQuestion();
        }

        updateUI();
    }

    /**
     * Moves to the previous question.
     */
    @FXML
    private void previousQuestion() {
        saveCurrentQuestion();

        if (this.currentIndex > 0) {
            this.currentIndex--;
            loadQuestion();
        }

        updateUI();
    }

    /**
     * Exports the created quiz to a JSON file.
     */
    @FXML
    private void export() {
        saveCurrentQuestion();

        if (this.quiz.getTotalQuestions() >= 1) {
            if(this.titleField == null || this.titleField.getText().isEmpty()) {
                QuizApp.showInfoPopup("Title field is required.");
                return;
            }

            if(this.authorField == null || this.authorField.getText().isEmpty()) {
                QuizApp.showInfoPopup("Author field is required.");
                return;
            }

            FileChooser fc = createFileChooser();
            File file = fc.showSaveDialog(this.root.getScene().getWindow());

            if (file != null) {
                new QuizRepository().saveQuiz(this.quiz, file.toPath());
            } else {
                QuizApp.showInfoPopup("No path chosen, export aborted.");
            }
        }
    }

    /**
     * Loads the current question into the input fields.
     */
    private void loadQuestion() {
        Question question = this.quiz.getQuestions().get(this.currentIndex);

        this.questionField.setText(question.getQuestionText());
        this.answerField.setText(question.getCorrectAnswer());
        this.falseField1.setText(question.getIncorrectAnswers().get(0));
        this.falseField2.setText(question.getIncorrectAnswers().get(1));
        this.falseField3.setText(question.getIncorrectAnswers().get(2));
    }

    /**
     * Updates button states and question counter.
     */
    private void updateUI() {
        this.questionCounter.setText((this.currentIndex + 1)
                + " / "
                + this.quiz.getTotalQuestions());

        removeQuestionButton.setDisable(this.quiz.getTotalQuestions() <= 1);
        previousButton.setDisable(this.currentIndex == 0);
        nextButton.setDisable(this.currentIndex == this.quiz.getTotalQuestions() - 1);
    }

    /**
     * Creates and configures a file chooser for exporting quizzes.
     *
     * @return a configured FileChooser
     */
    private FileChooser createFileChooser() {
        FileChooser fs = new FileChooser();

        String title = this.titleField.getText().replaceAll("\\s", "-").toLowerCase();
        String author = this.authorField.getText().replaceAll("\\s", "-").toLowerCase();

        fs.setInitialDirectory(new File(System.getProperty("user.home")));
        fs.setTitle("Save Quiz");
        fs.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("KataQuiz", "*.json"));
        fs.setInitialFileName(title + "-" + author + ".json");

        return fs;
    }
}
