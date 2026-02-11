package com.charles.kataquiz.repository;

import com.charles.kataquiz.model.UserQuiz;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class QuizRepository {

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public void saveQuiz(UserQuiz quiz, Path path) {
        try {
            String json = gson.toJson(quiz);
            Files.writeString(path, json);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save quiz", e);
        }
    }

    public UserQuiz loadQuiz(Path path) {

        try {
            String json = Files.readString(path);
            return gson.fromJson(json, UserQuiz.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load quiz", e);
        }
    }
}
