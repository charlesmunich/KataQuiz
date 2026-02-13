/*
 * Author: Charles Loeffler
 * Last Updated: 02/12/2026
 */

package com.charles.kataquiz.service;

import com.charles.kataquiz.model.Category;
import com.charles.kataquiz.model.Question;

import java.util.List;

/**
 * Service responsible for setting up a quiz.
 * This class acts as a wrapper around the TriviaApiClient.
 */
public class QuizSetupService {
    private TriviaApiClient apiClient;

    /**
     * Creates a new QuizSetupService and initializes the API client.
     */
    public QuizSetupService(){
        this.apiClient = new TriviaApiClient();
    }

    /**
     * Retrieves available trivia categories.
     *
     * @return a list of categories
     * @throws com.charles.kataquiz.Exception.TriviaApiException
     *         if the API request fails
     */
    public List<Category> getCategories() {
        return this.apiClient.fetchCategories();
    }

    /**
     * Creates a quiz by fetching questions for a given category.
     *
     * @param category the selected category
     * @param numQuestions the number of questions requested
     * @return a list of questions
     * @throws com.charles.kataquiz.Exception.TriviaApiException
     *         if the API request fails or returns an error
     */
    public List<Question> createQuiz(Category category, int numQuestions) {
        return this.apiClient.fetchQuestions(category.id(), numQuestions);
    }
}
