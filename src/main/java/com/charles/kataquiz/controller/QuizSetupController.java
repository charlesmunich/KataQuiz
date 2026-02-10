package com.charles.kataquiz.controller;

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

    @FXML
    public void initialize() {
        addCategories();
    }

    @FXML
    public void beginQuiz(){
    }

    private void addCategories(){
        TriviaApiClient getCategories = new TriviaApiClient();
        List<Category> categories = getCategories.fetchCategories();

        for(Category category : categories){
            MenuItem item = new MenuItem(category.getName());
            item.setOnAction(e -> {
                categoryMenu.setText(category.getName());
            });
            categoryMenu.getItems().add(item);
        }
    }
}
