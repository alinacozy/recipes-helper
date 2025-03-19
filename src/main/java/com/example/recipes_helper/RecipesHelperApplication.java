package com.example.recipes_helper;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipes_helper.model.Recipe;
import com.example.recipes_helper.model.RecipeCategory;
import com.example.recipes_helper.repository.RecipeRepository;


@SpringBootApplication
@RestController
public class RecipesHelperApplication {

	@Autowired
	private RecipeRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(RecipesHelperApplication.class, args);
	}

	@GetMapping("/hello")
	public String getHello(@RequestParam(defaultValue = "Alina and Sofia") String name) {
	  return String.format("Hello, %s!", name);
	}

	@GetMapping("/recipes")
	public String getRecipes() {
	
	  List<Recipe> recipes = (List<Recipe>) repository.findAll();
	  String result="";
	  for (Recipe r : recipes){
		result=result.concat(String.format("Название: %s. Категория: %s. Описание: %s.\n", r.getRecipeName(), r.getRecipeCategory(), r.getDescription()));
	  }
	  return result;
	}

	// @GetMapping("/recipes")
	// public String getRecipes(@RequestParam(defaultValue = "") String recipeCategory, @RequestParam(defaultValue = "") String productCategory) {
	
	//   List<Recipe> recipes = (List<Recipe>) repository.findAll();
	//   String result="";
	//   for (Recipe r : recipes){
	// 	result=result.concat(String.format("Название: %s. Категория: %s. Описание: %s.\n", r.getRecipeName(), r.getRecipeCategory(), r.getDescription()));
	//   }
	//   return result;
	// }

	@GetMapping("/recipes/{idRecipe}")
	public String getRecipe(@PathVariable(name = "idRecipe") Long idRecipe) {
	
	  Recipe recipe = repository.findByRecipeId(idRecipe);
	  return String.format("Название: %s. Категория: %s. Описание: %s.", recipe.getRecipeName(), recipe.getRecipeCategory(), recipe.getDescription());
	}


}

