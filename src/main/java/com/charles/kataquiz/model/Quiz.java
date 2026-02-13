/*
 * Author: Charles Loeffler
 * Last Updated: 02/12/2026
 */

package com.charles.kataquiz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a quiz containing a list of questions.
 */
public class Quiz {
    private List<Question> questions;

    /**
     * Creates an empty quiz with one default question.
     */
    public Quiz() {
        this.questions = new ArrayList<>();
        this.questions.add(new Question());
    }

    /**
     * Creates a quiz with the given list of questions.
     *
     * @param questions the list of questions
     */
    public Quiz(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * Returns the list of questions in the quiz.
     *
     * @return the list of questions
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Adds a new empty question to the quiz.
     */
    public void addQuestion() {
        questions.add(new Question());
    }

    /**
     * Removes a question at the specified index.
     * Ensures at least one question remains.
     *
     * @param index the index of the question to remove
     */
    public void removeQuestion(int index) {
        if (questions.size() > 1) {
            questions.remove(index);
        }
    }

    /**
     * Returns the total number of questions in the quiz.
     *
     * @return the number of questions
     */
    public int getTotalQuestions() {
        return questions.size();
    }
}
