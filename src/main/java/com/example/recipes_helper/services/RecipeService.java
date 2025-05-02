package com.example.recipes_helper.services;

import java.util.List;

import com.example.recipes_helper.DTO.RecipeWithIngredientsDTO;
import com.example.recipes_helper.model.Product;
import com.example.recipes_helper.model.ProductCategory;
import com.example.recipes_helper.model.Recipe;
import com.example.recipes_helper.model.RecipeCategory;


public interface RecipeService {
    Recipe getRecipeById(Long idRecipe);
    RecipeWithIngredientsDTO getRecipeWithIngredientsById(Long idRecipe, Long idUser);
    List<Product> getProductsByRecipeId(Long idRecipe);
    List<Recipe> getRecipesByCategoryForUser(Long idUser, RecipeCategory recipeCategory, ProductCategory productCategory, boolean available);
    List<Recipe> getRecipesForUser(Long idUser, RecipeCategory recipeCategory, ProductCategory productCategory);
    void decreaseProducts(Long idRecipe, Long idUser);
} 