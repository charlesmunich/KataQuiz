package com.charles.kataquiz.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private String title;
    private String author;
    private List<Question> questions;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.questions.add(new Question());
    }

    public Quiz(List<Question> questions) {
        this.title = "";
        this.author = "";
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion() {
        questions.add(new Question());
    }

    public void removeQuestion(int index) {
        if (questions.size() > 1) {
            questions.remove(index);
        }
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
