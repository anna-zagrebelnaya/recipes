package com.example.recipes.repository;

import com.example.recipes.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByCategoryIn(List<Recipe.Category> categories);

    List<Recipe> findByCaloriesBetween(Integer lower, Integer upper);

    List<Recipe> findByCategoryInAndCaloriesBetween(List<Recipe.Category> categories, Integer lower, Integer upper);
}
