/*
 * Author: Charles Loeffler
 * Last Updated: 02/12/2026
 */

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

/**
 * Main entry point for the KataQuiz application.
 * This class initializes the primary stage and provides
 * utility methods for switching scenes and displaying
 * information popups.
 */
public class QuizApp extends Application {

    /**
     * User agent stylesheet used for consistent application styling.
     */
    public static final String USER_AGENT_STYLESHEET = new PrimerLight().getUserAgentStylesheet();

    /**
     * The primary stage of the application.
     */
    private static Stage primaryStage;

    /**
     * Initializes the primary stage and loads the home screen.
     *
     * @param stage the primary stage provided by JavaFX
     */
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        stage.setTitle("KataQuiz");
        stage.setResizable(false);
        setScene("home.fxml");
        stage.show();
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Loads and sets a new scene from the given FXML file.
     *
     * @param fxml the name of the FXML file to load
     * @throws RuntimeException if the FXML file cannot be loaded
     */
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

    /**
     * Loads and sets a new scene from the given FXML file,
     * allowing access to the controller before displaying it.
     *
     * @param fxml the name of the FXML file to load
     * @param controllerConsumer a consumer used to configure the controller
     * @param <T> the controller type
     * @throws RuntimeException if the FXML file cannot be loaded
     */
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

    /**
     * Displays an informational popup dialog with the given message.
     *
     * @param message the message to display
     */
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
