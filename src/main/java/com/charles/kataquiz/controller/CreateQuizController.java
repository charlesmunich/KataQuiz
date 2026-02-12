package com.charles.kataquiz.controller;

import com.charles.kataquiz.QuizApp;
import com.charles.kataquiz.model.Question;
import com.charles.kataquiz.model.Quiz;
import com.charles.kataquiz.repository.QuizRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

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
    private Label questionCounter;

    @FXML
    public void initialize() {
        this.quiz = new Quiz();
        this.currentIndex = 0;

        loadQuestion();
        updateCounter();
    }

    @FXML
    private void goHome(){
        QuizApp.setScene("home.fxml");
    }

    private void saveCurrentQuestion() {
        Question question = quiz.getQuestions().get(this.currentIndex);

        question.setQuestionText(this.questionField.getText());
        question.setCorrectAnswer(this.answerField.getText());
        question.setIncorrectAnswer(0, falseField1.getText());
        question.setIncorrectAnswer(1, falseField2.getText());
        question.setIncorrectAnswer(2, falseField3.getText());
    }

    @FXML
    private void addQuestion() {
        saveCurrentQuestion();

        this.quiz.addQuestion();
        this.currentIndex = this.quiz.getTotalQuestions() - 1;
        loadQuestion();
        updateCounter();
    }

    @FXML
    private void removeQuestion() {
        if (this.quiz.getTotalQuestions() > 1) {
            this.quiz.removeQuestion(this.currentIndex);

            if (this.currentIndex >= this.quiz.getTotalQuestions()) {
                this.currentIndex = this.quiz.getTotalQuestions() - 1;
            }

            loadQuestion();
            updateCounter();
        }
    }

    @FXML
    private void nextQuestion() {
        saveCurrentQuestion();

        if (this.currentIndex < this.quiz.getTotalQuestions() - 1) {
            this.currentIndex++;
            loadQuestion();
            updateCounter();
        }
    }

    @FXML
    private void previousQuestion() {
        saveCurrentQuestion();

        if (this.currentIndex > 0) {
            this.currentIndex--;
            loadQuestion();
            updateCounter();
        }
    }

    @FXML
    private void export() {
        saveCurrentQuestion();

        if (this.quiz.getTotalQuestions() > 1) {
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

    private void loadQuestion() {
        Question question = this.quiz.getQuestions().get(this.currentIndex);

        this.questionField.setText(question.getQuestionText());
        this.answerField.setText(question.getCorrectAnswer());
        this.falseField1.setText(question.getIncorrectAnswers().get(0));
        this.falseField2.setText(question.getIncorrectAnswers().get(1));
        this.falseField3.setText(question.getIncorrectAnswers().get(2));
    }

    private void updateCounter() {
        this.questionCounter.setText((this.currentIndex + 1)
                + " / "
                + this.quiz.getTotalQuestions());
    }

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
