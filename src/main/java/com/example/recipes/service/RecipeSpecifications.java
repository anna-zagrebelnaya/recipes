package com.example.recipes.service;

import com.example.recipes.model.Recipe;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class RecipeSpecifications {
    public static Specification<Recipe> hasCategories(List<Recipe.Category> categories) {
        return (root, query, builder) -> root.get("category").in(categories);
    }

    public static Specification<Recipe> hasCaloriesBetween(Integer lowerBound, Integer upperBound) {
        if (upperBound == null) {
            return (root, query, builder) -> builder.greaterThan(root.get("calories"), lowerBound);
        }
        return (root, query, builder) -> builder.between(root.get("calories"), lowerBound, upperBound);
    }
}
