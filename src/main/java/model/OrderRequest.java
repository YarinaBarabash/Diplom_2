package model;

import java.util.ArrayList;
import java.util.List;

public class OrderRequest {
    private List<String> ingredients;

    public OrderRequest() {
        ingredients = new ArrayList<>();
    }

    public OrderRequest(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}