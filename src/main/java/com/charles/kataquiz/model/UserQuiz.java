package com.charles.kataquiz.model;

import java.util.ArrayList;
import java.util.List;

public class UserQuiz {

    private String title;
    private String author;
    private List<UserQuestion> questions;

    public UserQuiz() {
        this.questions = new ArrayList<>();
        this.questions.add(new UserQuestion());
    }

    public void init(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<UserQuestion> getQuestions() {
        return questions;
    }

    public void addQuestion() {
        questions.add(new UserQuestion());
    }

    public void removeQuestion(int index) {
        if (questions.size() > 1) {
            questions.remove(index);
        }
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}
