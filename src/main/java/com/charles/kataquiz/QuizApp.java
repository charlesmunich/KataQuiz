package com.charles.kataquiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class QuizApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        stage.setTitle("KataQuiz");
        stage.setResizable(false);
        setScene("home.fxml");
        stage.show();
    }

    public static void setScene(String fxml) {
        try {
            Parent root = FXMLLoader.load(
                    QuizApp.class.getResource("/fxml/" + fxml)
            );
            primaryStage.setScene(new Scene(root));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load " + fxml, e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
