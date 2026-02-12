package com.charles.kataquiz.service;

import com.charles.kataquiz.Exception.TriviaApiException;
import com.charles.kataquiz.util.HtmlDecoder;
import com.charles.kataquiz.model.Category;
import com.charles.kataquiz.model.Question;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class TriviaApiClient {
    private static final String CATEGORY_URL = "https://opentdb.com/api_category.php";
    private static final String QUESTION_URL = "https://opentdb.com/api.php";
    private final HttpClient client = HttpClient.newHttpClient();

    public List<Category> fetchCategories() {
        try{
            HttpRequest request =
                    HttpRequest.newBuilder().uri(URI.create(CATEGORY_URL)).GET().build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject root = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonArray categoriesJson = root.getAsJsonArray("trivia_categories");

            List<Category> categories = new ArrayList<>();

            for(JsonElement element : categoriesJson){
                JsonObject obj = element.getAsJsonObject();
                int id = obj.get("id").getAsInt();
                String name = obj.get("name").getAsString();

                categories.add(new Category(id, name));
            }

            return categories;
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new TriviaApiException("Fetch categories request interrupted.", e);
        } catch (IOException e) {
            throw new TriviaApiException("Failed to fetch categories.", e);
        }
    }

    public List<Question> fetchQuestions(int categoryId, int amount){
        try{
            String url =
                    QUESTION_URL +
                            "?amount=" + amount +
                            "&category=" + categoryId +
                            "&type=multiple";

            HttpRequest request =
                    HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject root = JsonParser.parseString(response.body()).getAsJsonObject();

            int responseCode = root.get("response_code").getAsInt();
            if(responseCode != 0){
                throw new TriviaApiException("Bad response code: " + responseCode);
            }

            JsonArray results = root.getAsJsonArray("results");
            List<Question> questions = new ArrayList<>();

            for(JsonElement element : results){
                JsonObject obj = element.getAsJsonObject();

                String questionText = HtmlDecoder.decode(obj.get("question").getAsString());
                String correctAnswer = HtmlDecoder.decode(obj.get("correct_answer").getAsString());

                JsonArray incorrectJson = obj.getAsJsonArray("incorrect_answers");

                List<String> incorrectAnswers = new ArrayList<>();
                for(JsonElement incorrectElement : incorrectJson){
                    incorrectAnswers.add(HtmlDecoder.decode(incorrectElement.getAsString()));
                }

                questions.add(new Question(
                        questionText,
                        correctAnswer,
                        incorrectAnswers
                ));
            }

            return questions;
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new TriviaApiException("Fetch questions request interrupted.", e);
        } catch (IOException e) {
            throw new TriviaApiException("Failed to fetch questions.", e);
        }
    }
}
