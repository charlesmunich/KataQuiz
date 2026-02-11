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

public class QuizSetupController {
    @FXML
    private MenuButton categoryMenu;

    @FXML
    private TextField numQuestionsField;

    private Category selectedCategory;

    private final QuizSetupService service = new QuizSetupService();

    @FXML
    public void initialize() {
        addCategories();
    }

    @FXML
    public void beginQuiz(){
        try{
            if(!numQuestionsField.getText().isEmpty()){
                int numQuestions = Integer.parseInt(numQuestionsField.getText());

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
                QuizApp.showInfoPopup("Number of questions must be defined.");
            }
        } catch (NumberFormatException e){
            QuizApp.showInfoPopup("Please enter a valid number.");
        } catch (TriviaApiException e){
            QuizApp.setScene("home.fxml");
            QuizApp.showInfoPopup(e.getMessage());
        }
    }

    private void addCategories(){
        this.categoryMenu.getItems().clear();

        try{
            List<Category> categories = this.service.getCategories();

            for(Category category : categories){
                MenuItem item = new MenuItem(category.getName());

                item.setOnAction(_ -> {
                    this.categoryMenu.setText(category.getName());
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
