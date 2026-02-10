package com.charles.kataquiz.controller;

import com.charles.kataquiz.Question;
import com.charles.kataquiz.QuizApp;
import com.charles.kataquiz.TriviaApiClient;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class QuizSetupController {
    @FXML
    private MenuButton categoryMenu;

    @FXML
    private TextField numQuestionsField;

    TriviaApiClient apiClient = new TriviaApiClient();
    private Category selectedCategory;

    @FXML
    public void initialize() {
        addCategories();
    }

    @FXML
    public void beginQuiz(){
        // TODO verify categorie and numquestions

        int numQuestions = Integer.parseInt(numQuestionsField.getText());

        List<Question> questions = apiClient.fetchQuestions(selectedCategory.getId(), numQuestions);

        for(Question question : questions){
            System.out.println(question);
        }

//        QuizApp.setScene("quiz.fxml", controller -> {
//            controller.startQuiz(questions);
//        });
    }

    private void addCategories(){
        List<Category> categories = this.apiClient.fetchCategories();

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
