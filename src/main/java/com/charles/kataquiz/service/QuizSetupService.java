package com.charles.kataquiz.service;

import com.charles.kataquiz.Exception.TriviaApiException;
import com.charles.kataquiz.QuizApp;
import com.charles.kataquiz.model.Category;
import com.charles.kataquiz.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizSetupService {
    TriviaApiClient apiClient;

    public QuizSetupService(){
        this.apiClient = new TriviaApiClient();
    }

    public List<Category> getCategories() {
        return this.apiClient.fetchCategories();
    }

    public List<Question> createQuiz(Category category, int numQuestions) {
        return this.apiClient.fetchQuestions(category.getId(), numQuestions);
    }
}
