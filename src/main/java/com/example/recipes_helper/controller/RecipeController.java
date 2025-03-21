package com.example.recipes_helper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipes_helper.model.Recipe;
import com.example.recipes_helper.model.RecipeCategory;
import com.example.recipes_helper.services.RecipeService;

@RestController
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
   

    @GetMapping("/hello")
	public String getHello(@RequestParam(defaultValue = "Alina and Sofia") String name) {
	  return String.format("Hello, %s!", name);
	}


	@GetMapping("/recipes")
	public String getRecipes(@RequestParam(required = false) RecipeCategory recipeCategory) {
		List<Recipe> recipes=recipeService.getRecipesByCategory(recipeCategory);
		String result="";
		for (Recipe r : recipes){
			result=result.concat(String.format("Название: %s. Категория: %s. Описание: %s.\n", r.getRecipeName(), r.getRecipeCategory(), r.getDescription()));
	  	}
		return result;
	}

	@GetMapping("/recipes/{idRecipe}")
	public String getRecipe(@PathVariable(name = "idRecipe") Long idRecipe) {
	  Recipe recipe = recipeService.getRecipeById(idRecipe);
	  return String.format("Название: %s. Категория: %s. Описание: %s.", recipe.getRecipeName(), recipe.getRecipeCategory(), recipe.getDescription());
	}
}
