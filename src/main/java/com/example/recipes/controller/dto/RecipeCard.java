package com.example.recipes.controller.dto;

import com.example.recipes.model.Recipe;

public class RecipeCard {

    private Long id;
    private String name;
    private String imageUrl;
    private Recipe.Category category;
    private Integer calories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Recipe.Category getCategory() {
        return category;
    }

    public void setCategory(Recipe.Category category) {
        this.category = category;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }
}
