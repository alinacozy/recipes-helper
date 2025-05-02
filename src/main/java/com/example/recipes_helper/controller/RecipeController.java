package com.example.recipes_helper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.recipes_helper.DTO.RecipeWithIngredientsDTO;
import com.example.recipes_helper.config.MyUserDetails;
import com.example.recipes_helper.model.ProductCategory;
import com.example.recipes_helper.model.Recipe;
import com.example.recipes_helper.model.RecipeCategory;
import com.example.recipes_helper.services.HistoryService;
import com.example.recipes_helper.services.RecipeService;

@Controller
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private HistoryService historyService;

    @GetMapping("/hello")
	@ResponseBody
	public String getHello(@RequestParam(defaultValue = "Alina and Sofia") String name) {
	  return String.format("Hello, %s!", name);
	}

	@GetMapping("/recipes") // все рецепты, здесь в параметрах передаются фильтры
	@ResponseBody
	public List<Recipe> getRecipes(@RequestParam(required = false) RecipeCategory recipeCategory, @RequestParam(required = false) ProductCategory productCategory) {
		return recipeService.getRecipesByCategory(recipeCategory, productCategory);
	}

	@GetMapping("/recipes/{idRecipe}") // конкретный рецепт с ингредиентами
	public String getRecipe(@AuthenticationPrincipal MyUserDetails userDetails, @PathVariable Long idRecipe, Model model) {
		Long currentUserId = userDetails.getId();
		RecipeWithIngredientsDTO recipeWithIngredients = recipeService.getRecipeWithIngredientsById(idRecipe, currentUserId);
		model.addAttribute("recipe", recipeWithIngredients);
		return "recipe.html";
	}

	@GetMapping("/user_recipes")
	@ResponseBody
	public List<Recipe> getRecipesForCurrentUser(@AuthenticationPrincipal MyUserDetails userDetails, @RequestParam(required = false) RecipeCategory recipeCategory, @RequestParam(required = false) ProductCategory productCategory) {
		Long currentUserId = userDetails.getId();
		return recipeService.getRecipesForUser(currentUserId, recipeCategory, productCategory);
	}

	@PostMapping("/recipes/cook")
	public String cookRecipe(@AuthenticationPrincipal MyUserDetails userDetails, @RequestParam Long recipeId){
		Long currentUserId = userDetails.getId();
		recipeService.decreaseProducts(recipeId, currentUserId);
		historyService.saveCookedRecipe(currentUserId, recipeId);
		return "redirect:/recipe_success";
	}
	
}
