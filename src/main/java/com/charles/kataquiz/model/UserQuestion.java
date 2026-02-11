package com.charles.kataquiz.model;

import java.util.ArrayList;
import java.util.List;

public class UserQuestion {

    private String questionText;
    private String correctAnswer;
    private List<String> incorrectAnswers;

    public UserQuestion() {
        this.questionText = "";
        this.correctAnswer = "";
        this.incorrectAnswers = new ArrayList<>();
        this.incorrectAnswers.add("");
        this.incorrectAnswers.add("");
        this.incorrectAnswers.add("");
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswer(int index, String value) {
        this.incorrectAnswers.set(index, value);
    }
}
