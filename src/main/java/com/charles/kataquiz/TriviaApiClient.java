package com.charles.kataquiz;

import com.charles.kataquiz.controller.Category;
import com.google.gson.*;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class TriviaApiClient {

    private static final String CATEGORY_URL =
            "https://opentdb.com/api_category.php";

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public List<Question> fetchQuestions(int categoryId){
        return null;
    }

    public List<Category> fetchCategories() {
        try{
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(CATEGORY_URL)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

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
        } catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        throw new RuntimeException("Failed to fetch categories");
    }
}
