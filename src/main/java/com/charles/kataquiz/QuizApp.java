package com.charles.kataquiz;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

public class QuizApp extends Application {
    public static final String USER_AGENT_STYLESHEET = new PrimerLight().getUserAgentStylesheet();
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        stage.setTitle("KataQuiz");
        stage.setResizable(false);
        setScene("home.fxml");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setScene(String fxml) {
        try {
            Parent root = FXMLLoader.load(
                    Objects.requireNonNull(QuizApp.class.getResource("/fxml/" + fxml))
            );

            Application.setUserAgentStylesheet(USER_AGENT_STYLESHEET);

            primaryStage.setScene(new Scene(root));
        } catch (NullPointerException | IOException e) {
            throw new RuntimeException("Failed to load " + fxml, e);
        }
    }

    public static <T> void setScene(String fxml, Consumer<T> controllerConsumer) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    QuizApp.class.getResource("/fxml/" + fxml)
            );
            Parent root = loader.load();

            T controller = loader.getController();
            controllerConsumer.accept(controller);

            Application.setUserAgentStylesheet(USER_AGENT_STYLESHEET);

            primaryStage.setScene(new Scene(root));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load " + fxml, e);
        }
    }

    public static void showInfoPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);

        Application.setUserAgentStylesheet(USER_AGENT_STYLESHEET);

        alert.getDialogPane().getStyleClass().add("root");

        alert.showAndWait();
    }
}
