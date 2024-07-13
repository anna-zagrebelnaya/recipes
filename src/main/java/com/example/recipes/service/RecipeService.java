package com.example.recipes.service;

import com.example.recipes.model.Recipe;
import com.example.recipes.model.Ingredient;
import com.example.recipes.model.Product;
import com.example.recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
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
