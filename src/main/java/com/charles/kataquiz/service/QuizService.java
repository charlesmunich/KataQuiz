package com.charles.kataquiz.service;

import com.charles.kataquiz.model.Question;
import com.charles.kataquiz.util.AnswerUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizService {
    private List<Question> questions;
    private int currentQuestionIndex;
    private final Map<Integer, List<String>> shuffledAnswers = new HashMap<>();
    private final Map<Integer, String> answers = new HashMap<>();

    public QuizService(List<Question> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
    }

    public void submitAnswer(String selectedAnswer) {
        this.answers.put(currentQuestionIndex, selectedAnswer);
    }

    public void nextQuestion(){
        this.currentQuestionIndex++;
    }

    public void previousQuestion(){
        this.currentQuestionIndex--;
    }

    public boolean isFirstQuestion(){
        return this.currentQuestionIndex == 0;
    }

    public boolean isLastQuestion(){
        return this.currentQuestionIndex == questions.size() - 1;
    }

    public List<String> getShuffledAnswers(Question question) {
        if (shuffledAnswers.containsKey(currentQuestionIndex)) {
            return shuffledAnswers.get(currentQuestionIndex);
        }

        List<String> shuffled = AnswerUtil.combineAndShuffle(question);
        shuffledAnswers.put(currentQuestionIndex, shuffled);

        return shuffled;
    }

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

    public List<Question> getQuestions() {
        return this.questions;
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public String getSavedAnswer() {
        return this.answers.get(currentQuestionIndex);
    }

    public int getCurrentQuestionNumber(){
        return this.currentQuestionIndex + 1;
    }

    public int getTotalNumberOfQuestions(){
        return this.questions.size();
    }
}
