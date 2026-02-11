package com.charles.kataquiz.model;

import java.util.List;

public class UserQuiz {
    public String title;
    public String author;
    public List<UserQuestion> questions;

    public void init(String title, String author) {
        this.title = title;
        this.author = author;
    }

    
}
