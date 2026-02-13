/*
 * Author: Charles Loeffler
 * Last Updated: 02/12/2026
 */

package com.charles.kataquiz.service;

import com.charles.kataquiz.QuizApp;
import com.charles.kataquiz.controller.QuizController;
import com.charles.kataquiz.model.Question;
import com.charles.kataquiz.model.Quiz;
import com.charles.kataquiz.repository.QuizRepository;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Service responsible for importing a quiz from a file
 * and starting it in the application.
 */
public class ImportService {

    /**
     * Loads a quiz from the given file path and starts it.
     *
     * @param path the path to the quiz file
     * @throws RuntimeException if the quiz cannot be loaded
     *         or the scene fails to initialize
     */
    public static void startQuiz(Path path) {
        QuizRepository repository = new QuizRepository();
        Quiz quiz = repository.loadQuiz(path);

        List<Question> questions = new ArrayList<>();
        for (Question uq : quiz.getQuestions()) {

            Question q = new Question(
                    uq.getQuestionText(),
                    uq.getCorrectAnswer(),
                    uq.getIncorrectAnswers()
            );

            questions.add(q);
        }

        QuizService quizService = new QuizService(questions);

        QuizApp.setScene("quiz.fxml", controller -> {
            QuizController qc = (QuizController) controller;
            qc.startImportedQuiz(quizService);
        });
    }
}
