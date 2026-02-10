package com.charles.kataquiz;

import java.util.List;

public class QuizService {
    private List<Question> questions;

    public void loadApiQuestions() {

    }

    public void loadUserQuestions() {

    }

    public Question getCurrentQuestion(int index){
        return null;
    }

    public boolean checkAnswer(Question question, int answerIndex){
        return false;
    }

    public List<String> getShuffledAnswers(Question question){
        return null;
    }

    public int getTotalQuestions(){
        return 0;
    }
}
