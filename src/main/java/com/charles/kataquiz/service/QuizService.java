/*
 * Author: Charles Loeffler
 * Last Updated: 02/12/2026
 */

package com.charles.kataquiz.service;

import com.charles.kataquiz.model.Question;
import com.charles.kataquiz.util.AnswerUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service that manages quiz progression, answer tracking,
 * and scoring logic.
 */
public class QuizService {
    private List<Question> questions;
    private int currentQuestionIndex;
    private final Map<Integer, List<String>> shuffledAnswers = new HashMap<>();
    private final Map<Integer, String> answers = new HashMap<>();

    /**
     * Creates a QuizService with the given questions.
     *
     * @param questions the list of quiz questions
     */
    public QuizService(List<Question> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
    }

    /**
     * Saves the selected answer for the current question.
     *
     * @param selectedAnswer the answer chosen by the user
     */
    public void submitAnswer(String selectedAnswer) {
        this.answers.put(currentQuestionIndex, selectedAnswer);
    }

    /**
     * Moves to the next question.
     */
    public void nextQuestion(){
        this.currentQuestionIndex++;
    }

    /**
     * Moves to the previous question.
     */
    public void previousQuestion(){
        this.currentQuestionIndex--;
    }

    /**
     * Checks if the current question is the first question.
     *
     * @return true if on the first question, false otherwise
     */
    public boolean isFirstQuestion(){
        return this.currentQuestionIndex == 0;
    }

    /**
     * Checks if the current question is the last question.
     *
     * @return true if on the last question, false otherwise
     */
    public boolean isLastQuestion(){
        return this.currentQuestionIndex == questions.size() - 1;
    }

    /**
     * Returns shuffled answer choices for the current question.
     * Shuffling is cached to keep answer order consistent.
     *
     * @param question the current question
     * @return a shuffled list of answers
     */
    public List<String> getShuffledAnswers(Question question) {
        if (shuffledAnswers.containsKey(currentQuestionIndex)) {
            return shuffledAnswers.get(currentQuestionIndex);
        }

        List<String> shuffled = AnswerUtil.combineAndShuffle(question);
        shuffledAnswers.put(currentQuestionIndex, shuffled);

        return shuffled;
    }

    /**
     * Calculates the user's final score.
     *
     * @return the total number of correct answers
     */
    public int getFinalScore() {
        int score = 0;

        for (int i = 0; i < this.questions.size(); i++) {

            String userAnswer = this.answers.get(i);
            String correctAnswer = this.questions.get(i).getCorrectAnswer();

            if (userAnswer != null && userAnswer.equals(correctAnswer)) {
                score++;
            }
        }

        return score;
    }

    /**
     * Returns all quiz questions.
     *
     * @return the list of questions
     */
    public List<Question> getQuestions() {
        return this.questions;
    }

    /**
     * Returns the current question.
     *
     * @return the current question
     */
    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    /**
     * Returns the saved answer for the current question.
     *
     * @return the selected answer, or null if none
     */
    public String getSavedAnswer() {
        return this.answers.get(currentQuestionIndex);
    }

    /**
     * Returns the current question number (1-based).
     *
     * @return the current question number
     */
    public int getCurrentQuestionNumber(){
        return this.currentQuestionIndex + 1;
    }

    /**
     * Returns the total number of questions in the quiz.
     *
     * @return the total number of questions
     */
    public int getTotalNumberOfQuestions(){
        return this.questions.size();
    }
}
