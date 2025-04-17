package com.example.recipes_helper.services;

import java.util.List;

import com.example.recipes_helper.model.Product;
import com.example.recipes_helper.model.Recipe;
import com.example.recipes_helper.model.RecipeCategory;


public interface RecipeService {
    Recipe getRecipeById(Long idRecipe);
    List<Product> getProductsByRecipeId(Long idRecipe);
    List<Recipe> getRecipesByCategory(RecipeCategory recipeCategory);
    List<Recipe> getRecipesForUser(Long idUser);
    void decreaseProducts(Long idRecipe, Long idUser);
} 