package com.example.recipes.repository;

import com.example.recipes.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByCategoryIn(List<Recipe.Category> categories);
}
