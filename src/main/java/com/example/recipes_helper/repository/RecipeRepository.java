package com.example.recipes_helper.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.example.recipes_helper.model.Recipe;
import com.example.recipes_helper.model.RecipeCategory;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{
  List<Recipe> findByRecipeName(String recipeName);

  List <Recipe> findByRecipeCategory(RecipeCategory recipeCategory);

  Recipe findByRecipeId(Long recipeId);

    
} 

