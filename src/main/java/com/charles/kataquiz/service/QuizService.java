package com.charles.kataquiz.service;

import com.charles.kataquiz.model.Question;
import com.charles.kataquiz.util.AnswerUtil;

import java.util.List;

public class QuizService {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private boolean hintUsed;
    private int maxQuestionIndex;

    public QuizService(List<Question> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.maxQuestionIndex = questions.size() - 1;
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public void submitAnswer(String selectedAnswer) {
        Question current = getCurrentQuestion();

        if(selectedAnswer.equals(current.getCorrectAnswer())){
            score++;
        }
    }

    public void nextQuestion(){
        this.currentQuestionIndex++;
    }

    public int getScore(){
        return this.score;
    }

    public boolean isOver(){
        return this.currentQuestionIndex >= maxQuestionIndex;
    }

    public List<String> getShuffledAnswers(Question question) {
        return AnswerUtil.combineAndShuffle(question);
    }
}
