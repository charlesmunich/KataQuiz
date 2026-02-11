package com.charles.kataquiz.controller;

import com.charles.kataquiz.model.UserQuestion;
import com.charles.kataquiz.model.UserQuiz;
import com.charles.kataquiz.repository.QuizRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class CreateQuizController {

    private UserQuiz quiz = new UserQuiz();
    private int currentIndex = 0;

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
    private TextField FalseField2;

    @FXML
    private TextField FalseField3;

    @FXML
    private Label questionsData;

    @FXML
    public void initialize() {
        loadQuestion();
        updateCounter();
    }

    private void saveCurrentQuestion() {
        UserQuestion q = quiz.getQuestions().get(currentIndex);

        q.setQuestionText(questionField.getText());
        q.setCorrectAnswer(answerField.getText());
        q.setIncorrectAnswer(0, falseField1.getText());
        q.setIncorrectAnswer(1, FalseField2.getText());
        q.setIncorrectAnswer(2, FalseField3.getText());
    }

    private void loadQuestion() {
        UserQuestion q = quiz.getQuestions().get(currentIndex);

        questionField.setText(q.getQuestionText());
        answerField.setText(q.getCorrectAnswer());
        falseField1.setText(q.getIncorrectAnswers().get(0));
        FalseField2.setText(q.getIncorrectAnswers().get(1));
        FalseField3.setText(q.getIncorrectAnswers().get(2));
    }

    private void updateCounter() {
        questionsData.setText((currentIndex + 1) + " / " + quiz.getTotalQuestions());
    }

    @FXML
    private void addQuestion() {
        saveCurrentQuestion();
        quiz.addQuestion();
        currentIndex = quiz.getTotalQuestions() - 1;
        loadQuestion();
        updateCounter();
    }

    @FXML
    private void removeQuestion() {
        if (quiz.getTotalQuestions() > 1) {
            quiz.removeQuestion(currentIndex);

            if (currentIndex >= quiz.getTotalQuestions()) {
                currentIndex = quiz.getTotalQuestions() - 1;
            }

            loadQuestion();
            updateCounter();
        }
    }

    @FXML
    private void nextQuestion() {
        saveCurrentQuestion();

        if (currentIndex < quiz.getTotalQuestions() - 1) {
            currentIndex++;
            loadQuestion();
            updateCounter();
        }
    }

    @FXML
    private void previousQuestion() {
        saveCurrentQuestion();

        if (currentIndex > 0) {
            currentIndex--;
            loadQuestion();
            updateCounter();
        }
    }

    @FXML
    private void export() {

        saveCurrentQuestion();
        quiz.init(titleField.getText(), authorField.getText()); //TODO change out with getters

        if (quiz.getTotalQuestions() < 1) {
            return;
        }

        FileChooser fc = createFileChooser();
        File file = fc.showSaveDialog(questionField.getScene().getWindow());
        if (file != null) {
            new QuizRepository().saveQuiz(this.quiz, file.toPath());
        }
    }

    private FileChooser createFileChooser() {
        FileChooser fs = new FileChooser();
        fs.setInitialDirectory(new File(System.getProperty("user.home")));
        fs.setTitle("Save Quiz");
        fs.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("KataQuiz", "*.json"));
        fs.setInitialFileName(
                titleField.getText().replaceAll("\\s", "-")
                + "-"
                + authorField.getText().replaceAll("\\s", "-")
                + ".json");
        return fs;
    }

}
