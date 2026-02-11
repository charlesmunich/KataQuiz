package com.charles.kataquiz.model;

import java.util.List;

public class Question {
    public String questionText;

    public String correctAnswer;
    public List<String> incorrectAnswers;
    public String category;
    public String difficulty;

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

    public String getCategory() {
        return this.category;
    }

    public String getDifficulty() {
        return this.difficulty;
    }
}
