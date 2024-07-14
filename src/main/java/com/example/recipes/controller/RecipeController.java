package com.example.recipes.controller;

import com.example.recipes.controller.dto.GroceryItem;
import com.example.recipes.controller.dto.GroceryList;
import com.example.recipes.model.Recipe;
import com.example.recipes.service.RecipeService;
import com.example.recipes.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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
                             @RequestPart(name="image", required = false) MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String imageUrl = recipeService.saveImage(image);
            recipe.setImageUrl(imageUrl);
        }
        return recipeService.saveRecipe(recipe);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable Long id,
                               @RequestPart("recipe") Recipe recipe,
                               @RequestPart(name="image", required = false) MultipartFile image) throws IOException {
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

    @PostMapping(value="/generate-grocery-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public GroceryList generateGroceryList(@RequestBody List<Long> recipeIds) {
        Map<Product, Integer> map = recipeService.generateGroceryList(recipeIds);
        List<GroceryItem> groceryItems = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry: map.entrySet()) {
            Product product = entry.getKey();
            groceryItems.add(new GroceryItem(product.getName(), entry.getValue(), product.getUnit().toString()));
        }
        return new GroceryList(groceryItems);
    }
}
