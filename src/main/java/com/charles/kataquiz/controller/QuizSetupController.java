package com.charles.kataquiz.controller;

import com.charles.kataquiz.model.Category;
import com.charles.kataquiz.model.Question;
import com.charles.kataquiz.QuizApp;
import com.charles.kataquiz.service.QuizSetupService;
import com.charles.kataquiz.service.TriviaApiClient;
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

    QuizSetupService service = new QuizSetupService();

    @FXML
    public void initialize() {
        addCategories();
    }

    @FXML
    public void beginQuiz(){
        // TODO verify categorie and numquestions

        int numQuestions = Integer.parseInt(numQuestionsField.getText());

        List<Question> questions = this.service.createQuiz(selectedCategory, numQuestions);

        QuizApp.setScene("quiz.fxml", controller -> {
            QuizController quizController = (QuizController) controller;
            quizController.startQuiz(questions);
        });

    }

    private void addCategories(){
        List<Category> categories = this.service.getCategories();

        for(Category category : categories){
            MenuItem item = new MenuItem(category.getName());

            item.setOnAction(e -> {
                this.categoryMenu.setText(category.getName());
                this.selectedCategory = category;
            });
            this.categoryMenu.getItems().add(item);
        }
    }
}
