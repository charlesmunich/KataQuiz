package com.charles.kataquiz.service;

import com.charles.kataquiz.model.Category;
import com.charles.kataquiz.model.Question;

import java.util.List;

public class QuizSetupService {
    private TriviaApiClient apiClient;

    public QuizSetupService(){
        this.apiClient = new TriviaApiClient();
    }

    public List<Category> getCategories() {
        return this.apiClient.fetchCategories();
    }

    public List<Question> createQuiz(Category category, int numQuestions) {
        return this.apiClient.fetchQuestions(category.id(), numQuestions);
    }
}
