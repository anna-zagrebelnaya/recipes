package com.example.recipes.service;

import com.example.recipes.controller.dto.FilterByCalories;
import com.example.recipes.model.Recipe;
import com.example.recipes.model.Ingredient;
import com.example.recipes.model.Product;
import com.example.recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ProductService productService;

    public List<Recipe> getRecipes(List<Recipe.Category> categories, FilterByCalories calories) {
        Specification<Recipe> spec = Specification.where(null);

        if (categories != null && !categories.isEmpty()) {
            spec = spec.and(RecipeSpecifications.hasCategories(categories));
        }

        if (calories != null && !calories.equals(FilterByCalories.ALL)) {
            spec = spec.and(RecipeSpecifications.hasCaloriesBetween(calories.getLowerBound(), calories.getUpperBound()));
        }

        return recipeRepository.findAll(spec);
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public Recipe saveRecipe(Recipe recipe) {
        // Ensure products are saved before saving ingredients
        for (Ingredient ingredient : recipe.getIngredients()) {
            Product product = ingredient.getProduct();
            product = productService.findOrCreateProduct(product);
            ingredient.setProduct(product);
        }
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public String saveImage(MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            throw new IOException("Empty file");
        }

        String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + filename);
        Files.createDirectories(path.getParent());
        Files.write(path, image.getBytes());

        return filename;
    }

    public Map<Product, Integer> generateGroceryList(List<Long> recipeIds) {
        List<Recipe> recipes = recipeRepository.findAllById(recipeIds);
        return recipes.stream()
                .flatMap(recipe -> recipe.getIngredients().stream())
                .collect(Collectors.groupingBy(Ingredient::getProduct, Collectors.summingInt(Ingredient::getQuantity)));
    }
}
