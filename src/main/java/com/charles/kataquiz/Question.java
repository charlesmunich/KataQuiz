package com.charles.kataquiz;

import java.util.List;

public class Question {
    public String questionText;

    public String correctAnswer;
    public List<String> incorrectAnswers;
    public String category;
    public String difficulty;

    public Question(
            String questionText,
            String correctAnswer,
            List<String> incorrectAnswers,
            String difficulty,
            String category
    ) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
        this.difficulty = difficulty;
        this.category = category;
        this.difficulty = difficulty;
        this.category = category;
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

    @Override
    public String toString() {
        return String.format(
                "Question:\n" +
                        "  Category   : %s\n" +
                        "  Difficulty : %s\n" +
                        "  Text       : %s\n" +
                        "  Correct    : %s\n" +
                        "  Incorrect  : %s\n",
                category,
                difficulty,
                questionText,
                correctAnswer,
                incorrectAnswers
        );
    }
}
