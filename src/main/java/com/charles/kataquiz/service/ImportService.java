package com.charles.kataquiz.service;

import com.charles.kataquiz.QuizApp;
import com.charles.kataquiz.controller.QuizController;
import com.charles.kataquiz.model.Question;
import com.charles.kataquiz.model.UserQuestion;
import com.charles.kataquiz.model.UserQuiz;
import com.charles.kataquiz.repository.QuizRepository;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ImportService {

    public static void startQuiz(Path path) {

        QuizRepository repository = new QuizRepository();

        UserQuiz userQuiz = repository.loadQuiz(path);

        List<Question> questions = new ArrayList<>();

        for (UserQuestion uq : userQuiz.getQuestions()) {

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
