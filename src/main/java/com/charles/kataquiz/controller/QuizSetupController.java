/*
 * Author: Charles Loeffler
 * Last Updated: 02/12/2026
 */

package com.charles.kataquiz.controller;

import com.charles.kataquiz.Exception.TriviaApiException;
import com.charles.kataquiz.model.Category;
import com.charles.kataquiz.model.Question;
import com.charles.kataquiz.QuizApp;
import com.charles.kataquiz.service.QuizSetupService;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.util.List;

/**
 * Controller responsible for handling quiz setup.
 * Allows the user to select a category and number of questions
 * before starting the quiz.
 */
public class QuizSetupController {

    private static final int MIN_NUM_QUESTIONS = 0;
    private static final int MAX_NUM_QUESTIONS = 50;
    private Category selectedCategory;
    private final QuizSetupService service = new QuizSetupService();

    @FXML
    private MenuButton categoryMenu;

    @FXML
    private TextField numQuestionsField;

    /**
     * Initializes the controller and loads available categories.
     */
    @FXML
    public void initialize() {
        addCategories();
    }

    /**
     * Returns the user to the home screen.
     */
    @FXML
    private void goHome(){
        QuizApp.setScene("home.fxml");
    }

    /**
     * Validates input and starts the quiz if valid.
     */
    @FXML
    public void beginQuiz(){
        try{
            if(!numQuestionsField.getText().isEmpty()){
                int numQuestions = Integer.parseInt(numQuestionsField.getText());

                if(numQuestions > MIN_NUM_QUESTIONS && numQuestions <= MAX_NUM_QUESTIONS){
                    if(selectedCategory != null){
                        List<Question> questions =
                                this.service.createQuiz(selectedCategory, numQuestions);

                        QuizApp.setScene("quiz.fxml", controller -> {
                            QuizController quizController = (QuizController) controller;
                            quizController.startQuiz(questions);
                        });
                    } else {
                        QuizApp.showInfoPopup("Please select a category.");
                    }
                } else {
                    QuizApp.showInfoPopup("Please enter a number between 1 and 50");
                }
            } else {
                QuizApp.showInfoPopup("Number of questions must be defined.");
            }
        } catch (NumberFormatException e){
            QuizApp.showInfoPopup("Please enter a valid number.");
        } catch (TriviaApiException e){
            QuizApp.setScene("home.fxml");
            QuizApp.showInfoPopup(e.getMessage());
        }
    }

    /**
     * Fetches categories from the API and populates the category menu.
     */
    private void addCategories(){
        this.categoryMenu.getItems().clear();

        try{
            List<Category> categories = this.service.getCategories();

            for(Category category : categories){
                MenuItem item = new MenuItem(category.name());

                item.setOnAction(actionEvent -> {
                    this.categoryMenu.setText(category.name());
                    this.selectedCategory = category;
                });
                this.categoryMenu.getItems().add(item);
            }
        } catch (TriviaApiException e){
            QuizApp.setScene("home.fxml");
            QuizApp.showInfoPopup(e.getMessage());
        }
    }
}
