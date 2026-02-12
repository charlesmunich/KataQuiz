package com.charles.kataquiz.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String questionText;
    private String correctAnswer;
    private List<String> incorrectAnswers;

    public Question() {
        this.questionText = "";
        this.correctAnswer = "";
        this.incorrectAnswers = new ArrayList<>();
        this.incorrectAnswers.add("");
        this.incorrectAnswers.add("");
        this.incorrectAnswers.add("");
    }

    public Question(String questionText, String correctAnswer, List<String> incorrectAnswers) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    public List<String> getIncorrectAnswers() {
        return this.incorrectAnswers;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setIncorrectAnswer(int index, String value) {
        this.incorrectAnswers.set(index, value);
    }
}
