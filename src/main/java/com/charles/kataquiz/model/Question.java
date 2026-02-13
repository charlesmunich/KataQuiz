/*
 * Author: Charles Loeffler
 * Last Updated: 02/12/2026
 */

package com.charles.kataquiz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a multiple-choice question.
 * A question contains the question text,
 * one correct answer, and three incorrect answers.
 */
public class Question {
    private String questionText;
    private String correctAnswer;
    private List<String> incorrectAnswers;

    /**
     * Creates an empty question with placeholder answers.
     */
    public Question() {
        this.questionText = "";
        this.correctAnswer = "";
        this.incorrectAnswers = new ArrayList<>();
        this.incorrectAnswers.add("");
        this.incorrectAnswers.add("");
        this.incorrectAnswers.add("");
    }

    /**
     * Creates a question with the given values.
     *
     * @param questionText the question text
     * @param correctAnswer the correct answer
     * @param incorrectAnswers the list of incorrect answers
     */
    public Question(String questionText, String correctAnswer, List<String> incorrectAnswers) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    /**
     * Returns the question text.
     *
     * @return the question text
     */
    public String getQuestionText() {
        return this.questionText;
    }

    /**
     * Returns the correct answer.
     *
     * @return the correct answer
     */
    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    /**
     * Returns the list of incorrect answers.
     *
     * @return the incorrect answers
     */
    public List<String> getIncorrectAnswers() {
        return this.incorrectAnswers;
    }

    /**
     * Sets the question text.
     *
     * @param questionText the new question text
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Sets the correct answer.
     *
     * @param correctAnswer the new correct answer
     */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Sets an incorrect answer at the given index.
     *
     * @param index the index of the incorrect answer
     * @param value the new incorrect answer value
     */
    public void setIncorrectAnswer(int index, String value) {
        this.incorrectAnswers.set(index, value);
    }
}
