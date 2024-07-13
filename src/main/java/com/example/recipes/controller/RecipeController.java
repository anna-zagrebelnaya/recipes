package com.example.recipes.controller;

import com.example.recipes.model.Recipe;
import com.example.recipes.service.RecipeService;
import com.example.recipes.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @PostMapping
    public Recipe saveRecipe(@RequestPart("recipe") Recipe recipe,
                             @RequestPart("image") MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String imageUrl = recipeService.saveImage(image);
            recipe.setImageUrl(imageUrl);
        }
        return recipeService.saveRecipe(recipe);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable Long id,
                               @RequestPart("recipe") Recipe recipe,
                               @RequestPart("image") MultipartFile image) throws IOException {
        recipe.setId(id);
        if (image != null && !image.isEmpty()) {
            String imageUrl = recipeService.saveImage(image);
            recipe.setImageUrl(imageUrl);
        }
        return recipeService.saveRecipe(recipe);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }

    @PostMapping("/generate-grocery-list")
    public Map<Product, Integer> generateGroceryList(@RequestBody List<Long> recipeIds) {
        return recipeService.generateGroceryList(recipeIds);
    }
}
