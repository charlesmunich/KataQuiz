/*
 * Author: Charles Loeffler
 * Last Updated: 02/12/2026
 */

package com.charles.kataquiz.repository;

import com.charles.kataquiz.model.Quiz;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Repository responsible for saving and loading quizzes
 * to and from JSON files.
 */
public class QuizRepository {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    /**
     * Saves a quiz to the specified file path in JSON format.
     *
     * @param quiz the quiz to save
     * @param path the file path to write to
     * @throws RuntimeException if the file cannot be written
     */
    public void saveQuiz(Quiz quiz, Path path) {
        try {
            String json = gson.toJson(quiz);
            Files.writeString(path, json);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save quiz", e);
        }
    }

    /**
     * Loads a quiz from the specified file path.
     *
     * @param path the file path to read from
     * @return the loaded quiz
     * @throws RuntimeException if the file cannot be read
     */
    public Quiz loadQuiz(Path path) {
        try {
            String json = Files.readString(path);
            return gson.fromJson(json, Quiz.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load quiz", e);
        }
    }
}
